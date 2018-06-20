package br.senai.sp.info.pweb.keeper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.pweb.keeper.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.models.Patrimonio;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioDAO patrimonioDAO;
	
	public List<Patrimonio> buscarTodos() {
		return patrimonioDAO.buscarTodos();
	}
	
	public Patrimonio buscar(Long id) throws NadaEncontradoException {
		
		Patrimonio patrimonioBuscado = patrimonioDAO.buscar(id);
		
		if (patrimonioBuscado == null) {
			throw new NadaEncontradoException();
		}
		
		return patrimonioBuscado;
	}
	
}
