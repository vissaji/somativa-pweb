package br.senai.sp.info.pweb.keeper.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.pweb.keeper.dao.AmbienteDAO;
import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.models.Ambiente;

@Service
public class AmbienteService {

	@Autowired
	private AmbienteDAO ambienteDAO;
	
	public List<Ambiente> buscarTodos() {
		return ambienteDAO.buscarTodos();
	}
	
	public Ambiente buscar(Long id) throws NadaEncontradoException {
		
		Ambiente ambienteBuscado = ambienteDAO.buscar(id);
		
		if (ambienteBuscado == null) {
			throw new NadaEncontradoException();
		}
		
		return ambienteBuscado;
	}
	
}
