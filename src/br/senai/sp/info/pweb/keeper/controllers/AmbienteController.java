package br.senai.sp.info.pweb.keeper.controllers;

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
import br.senai.sp.info.pweb.keeper.models.Ambiente;

@Controller
@RequestMapping("/app")
public class AmbienteController {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	@GetMapping("/adm/ambiente/novo")
	public String abrirNovoAmbiente(Model model) {
		model.addAttribute("ambiente", new Ambiente());
		
		return "ambiente/form";
	}
	
	@GetMapping("/ambiente/lista")
	public String abrirListaAmbientes(Model model) {
		model.addAttribute("ambientes", ambienteDAO.buscarTodos());
		
		return "ambiente/lista";
	}
	
	@GetMapping("/adm/ambiente/editar")
	public String editarAmbiente(Model model,
			@RequestParam(name = "id", required = true) Long id){
		
		model.addAttribute("ambiente", ambienteDAO.buscar(id));
		
		return "ambiente/form";
	}
	
	@GetMapping("/adm/ambiente/deletar")
	public String deletarAmbiente(@RequestParam(name = "id", required = true) Long id,
			@Valid Ambiente ambiente, BindingResult brAmbiente){
		
		try {
			ambienteDAO.deletar(ambienteDAO.buscar(id));	
		} catch (Exception e) {
			brAmbiente.addError(new FieldError("ambiente", "nome", 
					"Ambiente não pode ser deletado! Itens linkados a esse"));
			return "ambiente/form";
		}
		
		return "redirect:/app/ambiente/lista";
	}

	@PostMapping("/adm/ambiente/salvar")
	public String salvarAmbiente(@Valid Ambiente ambiente, BindingResult brAmbiente, 
			@RequestParam(name = "id", required = false) Long id, Model model) {
		
		if (ambienteDAO.buscarPorNome(ambiente.getNome()) != null) {
			brAmbiente.addError(new FieldError("ambiente", "nome", 
					"Ambiente: " + ambiente.getNome() + " já existe"));
		}
		
		if (brAmbiente.hasErrors()) {
			return "ambiente/form";
		}
		
		if (ambiente.getId() == null) {
			ambienteDAO.persistir(ambiente);
		} else {
			ambienteDAO.editar(ambiente);
		}
		
		return "redirect:/app/ambiente/lista";
	}
	
}
