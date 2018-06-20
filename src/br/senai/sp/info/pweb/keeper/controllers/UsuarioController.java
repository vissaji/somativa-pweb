package br.senai.sp.info.pweb.keeper.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.keeper.dao.UsuarioDAO;
import br.senai.sp.info.pweb.keeper.models.TiposUsuario;
import br.senai.sp.info.pweb.keeper.models.Usuario;
import br.senai.sp.info.pweb.keeper.utils.EmailUtils;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping("/app/adm/usuario/novo")
	public String abrirNovoUsuairo(Model model) {
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/form";
	}
	
	@GetMapping("/app/adm/usuario/lista")
	public String abrirListaUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioDAO.buscarTodos());
		
		return "usuario/lista";
	}
	
	@GetMapping("/app/adm/usuario/editar")
	public String editarUsuario(Model model,
			@RequestParam(name = "id", required = true) Long id, 
			HttpServletResponse response) throws IOException {
		
		model.addAttribute("usuario", usuarioDAO.buscar(id));
		
		return "usuario/form";
	}
	
	@GetMapping("/app/adm/usuario/deletar")
	public String deletarUsuario(@RequestParam(required = true) Long id,
			@Valid Usuario usuario, BindingResult brUsuario){
		
		try {
			usuarioDAO.deletar(usuarioDAO.buscar(id));
		} catch (Exception e) {
			brUsuario.addError(new FieldError(
					"usuario", "nome", "Usuário não pode ser deletado! Itens linkados a esse"));
			return "usuario/form";
		}
		
		return "redirect:/app/adm/usuario/lista";
	}
	
	@PostMapping("/app/adm/usuario/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "confirmarSenha", required = false) String confirmar,
			@RequestParam(name = "administrador", required = false) Boolean administrador) {
		
		if (usuario.getId() == null) {
			if (!confirmar.equals(usuario.getSenha())) {
				brUsuario.addError(new FieldError("usuario", "senha", "As senhas não são iguais"));
			}
			
			if (usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
				brUsuario.addError(new FieldError("usuario", "email", "O email já foi utilizado"));
			}
								
			if (brUsuario.hasErrors()) {
				return "usuario/form";
			}
		} else {
			if (brUsuario.hasFieldErrors("nome") || 
				brUsuario.hasFieldErrors("sobrenome")) {
				return "usuario/form";
			}
		}
		
		if (administrador != null) {
			usuario.setTipo(TiposUsuario.ADMINISTRADOR);
		} else {
			usuario.setTipo(TiposUsuario.COMUM);
		}
		
		if (usuario.getId() == null) {
			usuario.hashearSenha();
			usuarioDAO.persistir(usuario);
/*			
			String titulo = "Mensagem de confirmação";
			String corpo = "Olá, " + usuario.getNome() + ", seja bem-vindo ao Keeper!";
			

			try {
				EmailUtils.enviarMensagem(titulo, corpo, usuario.getEmail());
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
*/
		} else {
			Usuario usuarioEditado = usuarioDAO.buscar(usuario.getId());
			BeanUtils.copyProperties(usuario, usuarioEditado, "id", "email", "senha");
	
			usuarioDAO.editar(usuarioEditado);
		}
		
		return "redirect:/app/adm/usuario/lista";
	}
	
	@PostMapping("/autenticar")
	public String autenticarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, 
			BindingResult brUsuario, HttpSession session) {

		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha"))	{
			return "index";
		}
		
		usuario.hashearSenha();
		
		Usuario usuarioAutenticado = usuarioDAO
				.buscarPorEmailSenha(usuario.getEmail(), usuario.getSenha());
		
		if (usuarioAutenticado == null) {
			brUsuario.addError(new FieldError("usuario", "email", "E-mail ou senha inválido"));
			
			return "index";
		}
		
		session.setAttribute("usuarioAutenticado", usuarioAutenticado);
		
		return "redirect:/app/";
	}
	
	@GetMapping({"/sair"})
	public String logoutUsuario(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}

}
