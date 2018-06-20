package br.senai.sp.info.pweb.keeper.ws.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.services.ItemService;

@RestController
@RequestMapping("/rest/itens")

public class ItemRestController {

	@Autowired
	private ItemService itemService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(itemService.buscarItem(id));
		} catch (NadaEncontradoException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		try {
			return ResponseEntity.ok(itemService.buscarTodosItens());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
