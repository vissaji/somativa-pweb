package br.senai.sp.info.pweb.keeper.dao;

import br.senai.sp.info.pweb.keeper.models.Ambiente;

public interface AmbienteDAO extends DAO<Ambiente> {
	
	public Ambiente buscarPorNome(String nome);
	
}
