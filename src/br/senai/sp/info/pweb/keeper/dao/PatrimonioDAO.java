package br.senai.sp.info.pweb.keeper.dao;

import br.senai.sp.info.pweb.keeper.models.Patrimonio;

public interface PatrimonioDAO extends DAO<Patrimonio>{

	public Patrimonio buscarPorNome(String nome);
	
}
