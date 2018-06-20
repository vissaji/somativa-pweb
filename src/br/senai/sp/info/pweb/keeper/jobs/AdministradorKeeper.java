package br.senai.sp.info.pweb.keeper.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.keeper.dao.UsuarioDAO;
import br.senai.sp.info.pweb.keeper.models.TiposUsuario;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Component

public class AdministradorKeeper implements ApplicationListener<ApplicationEvent>{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public void onApplicationEvent(ApplicationEvent appEvent) {
		
		Usuario admin = new Usuario();
		admin.setEmail("admin@gmail.com");
		admin.setNome("Administrador");
		admin.setSenha("123456");
		admin.setSobrenome("da empresa Keeper");
		admin.setTipo(TiposUsuario.ADMINISTRADOR);
		
		admin.hashearSenha();
		
		if (usuarioDAO.buscarPorEmail(admin.getEmail()) == null) {
			usuarioDAO.persistir(admin);
			System.out.println("Administrador cadastrado com sucesso!");
		} else {
			System.out.println("Administrador já existe!");	
		}
	}

}
