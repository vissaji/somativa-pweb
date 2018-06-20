package br.senai.sp.info.pweb.keeper.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.keeper.dao.AmbienteDAO;
import br.senai.sp.info.pweb.keeper.dao.ItemDAO;
import br.senai.sp.info.pweb.keeper.dao.MovimentacaoDAO;
import br.senai.sp.info.pweb.keeper.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.keeper.models.Item;
import br.senai.sp.info.pweb.keeper.models.Movimentacao;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Controller
@RequestMapping("/app")
public class ItemController {

	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@GetMapping({"", "/"})
	public String abrirItemLista(Model model) {
		model.addAttribute("itens", itemDAO.buscarTodos());
		
		return "item/lista";
	}
	
	@GetMapping("/movimentacao/lista")
	public String abrirMovimentacaoLista(Model model) {
		model.addAttribute("movimentacoes", movimentacaoDAO.buscarTodos());
		
		return "movimentacao/lista";
	}
	
	@GetMapping("/item/novo")
	public String abrirNovoItem(Model model) {
		model.addAttribute("item", new Item());		
		model.addAttribute("patrimonios", patrimonioDAO.buscarTodos());
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		
		return "item/form";
	}
	
	@GetMapping("/item/editar")
	public String editarItem(@RequestParam(name = "id", required = true) Long id, Model model) {

		model.addAttribute("item", itemDAO.buscar(id));
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());	
	
		return "item/form";
	}
	
	@GetMapping("/adm/item/deletar")
	public String deletarItem(@Valid Item item, BindingResult brItem,
			@RequestParam(name = "id", required = true) Long id, Model model) {

		try {
			itemDAO.deletar(itemDAO.buscar(id));
		} catch (Exception e) {
			model.addAttribute("patrimonios", patrimonioDAO.buscarTodos());
			model.addAttribute("ambientes", ambienteDAO.buscarTodos());		
			
			brItem.addError(new FieldError("item", "patrimonio", 
					"Item não pode ser deletado! Movimentações linkados a esse"));
			return "item/form";
		}

		return "redirect:/app/movimentacao/lista";
	}
	
	@GetMapping("/adm/movimentacao/cancelar")
	public String deletarMovimentacao(@RequestParam(name = "id", required = true) Long id) {
		
		movimentacaoDAO.deletar(movimentacaoDAO.buscar(id));
		
		return "redirect:/app";
	}
	
	@PostMapping("/item/salvar")
	public String salvarItem(@Valid Item item, BindingResult brItem, 
			@RequestParam(name = "id", required = false) Long id, 
			Model model, HttpSession session) {
		
		if (item.getId() == null) {
			Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
			item.setUsuario(usuarioAutenticado);
			item.setData(new Date());
			
			itemDAO.persistir(item);
		} else {
			Item itemEditado = itemDAO.buscar(item.getId());
						
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setOrigem(itemEditado.getAmbiente());
			movimentacao.setItem(itemEditado);
			
			itemEditado.setAmbiente(item.getAmbiente());
			
			movimentacao.setDestino(item.getAmbiente());
			movimentacao.setDataMovimentacao(new Date());
			
			Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
			movimentacao.setTransportador(usuarioAutenticado);
			
			movimentacaoDAO.persistir(movimentacao);
			itemDAO.editar(itemEditado);
		}
		
		return "redirect:/app";
	}
	
}
