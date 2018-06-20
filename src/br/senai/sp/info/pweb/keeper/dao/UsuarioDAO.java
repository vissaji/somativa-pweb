package br.senai.sp.info.pweb.keeper.dao;

import br.senai.sp.info.pweb.keeper.models.Usuario;

public interface UsuarioDAO extends DAO<Usuario>{

	public Usuario buscarPorEmail(String email);
	
	public Usuario buscarPorEmailSenha(String email, String senha);
	
}
