package br.senai.sp.info.pweb.keeper.ws.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.keeper.models.Movimentacao;
import br.senai.sp.info.pweb.keeper.services.MovimentacaoService;
import br.senai.sp.info.pweb.keeper.utils.MapUtils;

@RestController
@RequestMapping("/rest/movimentacaos")

public class MovimentacaoRestController {

	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(movimentacaoService.buscarMovimentacao(id));
		} catch (NadaEncontradoException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		try {
			return ResponseEntity.ok(movimentacaoService.buscarTodosMovimentacaos());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Object> salvarMovimentacao(@PathVariable Long id, @RequestBody @Valid 
			Movimentacao movimentacao, BindingResult bindingResult) {
		try {
			return ResponseEntity.ok(movimentacaoService.cadastrar(id, movimentacao, bindingResult));
		} catch (ValidacaoException e) {
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapa(bindingResult));
		}
	}
	
}
