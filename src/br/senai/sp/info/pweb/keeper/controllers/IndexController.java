package br.senai.sp.info.pweb.keeper.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.senai.sp.info.pweb.keeper.dao.UsuarioDAO;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Controller
public class IndexController {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@GetMapping(value = {"/", ""})
	public String abrirLogin(Model model, HttpSession session) {
		if (session.getAttribute("usuarioAutenticado") != null) {
			return "redirect:/app";
		}
		
		model.addAttribute("usuario", new Usuario());
		
		return "index";
	}
	
}
