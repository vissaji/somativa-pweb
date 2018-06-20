package br.senai.sp.info.pweb.keeper.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.keeper.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.keeper.models.Categoria;
import br.senai.sp.info.pweb.keeper.models.Patrimonio;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Controller
@RequestMapping("/app")
public class PatrimonioController {

	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@GetMapping("/adm/patrimonio/novo")
	public String abrirNovoAmbiente(Model model) {
		model.addAttribute("patrimonio", new Patrimonio());		
		model.addAttribute("categoria", Categoria.values());
		
		return "patrimonio/form";
	}
	
	@GetMapping("/patrimonio/lista")
	public String abrirListaPatrimonios(Model model) {
		model.addAttribute("patrimonios", patrimonioDAO.buscarTodos());
		
		return "patrimonio/lista";
	}
	
	@GetMapping("/adm/patrimonio/editar")
	public String editarPatrimonio(Model model,
			@RequestParam(name = "id", required = true) Long id) {

		model.addAttribute("patrimonio", patrimonioDAO.buscar(id));
		model.addAttribute("categoria", Categoria.values());		
		
		return "patrimonio/form";
	}
	
	@GetMapping("/adm/patrimonio/deletar")
	public String deletarPatrimonio(@RequestParam(name = "id", required = true) Long id,
			@Valid Patrimonio patrimonio, BindingResult brPatrimonio) {
		
		try {
			patrimonioDAO.deletar(patrimonioDAO.buscar(id));	
		} catch (Exception e) {
			brPatrimonio.addError(new FieldError("patrimonio", "nome", 
					"Patrimônio não pode ser deletado! Itens linkados a esse"));
			
			return "patrimonio/form";
		}
		
		return "redirect:/app/patrimonio/lista";
	}
	
	@PostMapping("/adm/patrimonio/salvar")
	public String salvarPatrimonio(@Valid Patrimonio patrimonio, BindingResult brPatrimonio, 
			@RequestParam(name = "id", required = false) Long id, 
			Model model, HttpSession session) {
		
		if (patrimonioDAO.buscarPorNome(patrimonio.getNome()) != null) {
			model.addAttribute("categoria", Categoria.values());
			
			brPatrimonio.addError(new FieldError("patrimonio", "nome", 
					"Patrimônio " + patrimonio.getNome() + " já cadastrado"));
		}
		
		if (brPatrimonio.hasErrors()) {
			model.addAttribute("categoria", Categoria.values());
			
			return "patrimonio/form";
		}
		
		if (patrimonio.getId() == null) {
			Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
			patrimonio.setUsuario(usuarioAutenticado);
			patrimonio.setDataCadastro(new Date());
			
			patrimonioDAO.persistir(patrimonio);
		} else {
			Patrimonio patrimonioEditado = patrimonioDAO.buscar(id);
			BeanUtils.copyProperties(patrimonio, patrimonioEditado, "id", "usuario", "dataCadastro");
			
			patrimonioDAO.editar(patrimonioEditado);
		}
		
		return "redirect:/app/patrimonio/lista";
	}
	
}
