package br.senai.sp.info.pweb.keeper.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.pweb.keeper.models.Usuario;

@Component

public class AutenticacaoInterceptorKeeper extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler)throws Exception {

		boolean autenticacao = request.getRequestURI().contains("/app");
		boolean administrador = request.getRequestURI().contains("/adm");

		Usuario usuarioAutenticado = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
		boolean logado = usuarioAutenticado != null;
		
		if (autenticacao && !logado) {
			return false;
		} else if (administrador && !usuarioAutenticado.getAdministrador()) {
			return false;
		} else {
			return true;
		}
	}
	
}
