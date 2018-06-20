package br.senai.sp.info.pweb.keeper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.pweb.keeper.dao.ItemDAO;
import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.models.Item;

@Service
public class ItemService {

	@Autowired
	private ItemDAO itemDAO;
	
	public List<Item> buscarTodosItens() {
		return itemDAO.buscarTodos();
	}
	
	public Item buscarItem(Long id) throws NadaEncontradoException {
		Item itemBuscado = itemDAO.buscar(id);
		
		if (itemBuscado == null) {
			throw new NadaEncontradoException();
		}
		
		return itemBuscado;
	}

}
