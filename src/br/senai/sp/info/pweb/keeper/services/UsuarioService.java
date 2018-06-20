package br.senai.sp.info.pweb.keeper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.pweb.keeper.dao.UsuarioDAO;
import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public Usuario buscarPorEmailSenha(Usuario usuario, BindingResult brUsuario) 
			throws ValidacaoException, NadaEncontradoException {
		
		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			throw new ValidacaoException();
		}
		
		usuario.hashearSenha();
		Usuario usuarioBanco = usuarioDAO.buscarPorEmailSenha(usuario.getEmail(), usuario.getSenha()); 
		
		if (usuarioBanco == null) {
			throw new NadaEncontradoException();
		}
		
		return usuarioBanco;
	}
}
