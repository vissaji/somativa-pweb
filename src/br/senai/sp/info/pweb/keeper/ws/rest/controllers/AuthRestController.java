package br.senai.sp.info.pweb.keeper.ws.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.keeper.models.Usuario;
import br.senai.sp.info.pweb.keeper.services.UsuarioService;
import br.senai.sp.info.pweb.keeper.utils.JwtUtils;
import br.senai.sp.info.pweb.keeper.utils.MapUtils;

@RestController
@RequestMapping("/rest/auth")

public class AuthRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/jwt")
	public ResponseEntity<Object> gerarJWT(@RequestBody @Valid Usuario usuario, BindingResult bindingResult){
		
		try {
			Usuario usuarioBanco = usuarioService.buscarPorEmailSenha(usuario, bindingResult);
			
			Map<String, String> mapaToken = new HashMap<>();
			mapaToken.put("token", JwtUtils.gerarToken(usuarioBanco));
			
			return ResponseEntity.ok(mapaToken); 
		} catch (ValidacaoException e) {
			
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapa(bindingResult)); 
		} catch (NadaEncontradoException e) {
			
			return ResponseEntity.notFound().header("X-Reason", "Nada encontrado").build();
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}
