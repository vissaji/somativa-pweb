package br.senai.sp.info.pweb.keeper.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.senai.sp.info.pweb.keeper.models.Usuario;
import br.senai.sp.info.pweb.keeper.utils.JwtUtils;

@Component
public class JwtFilterKeeper extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("OIIIIIIIIIIIIi");
		
		HttpServletRequest request = (HttpServletRequest) req;
		String authorization = request.getHeader("Authorization");
		
		if (authorization != null) {
			if (authorization.matches("(Bearer )(\\w|\\.|\\-)+")) { 
				String token = authorization.split(" ")[1];
				
				try {
					JwtUtils.certificarToken(token);
					
					Usuario usuarioToken = JwtUtils.extrairUsuarioToken(token);
					SecurityContextHolder.getContext().setAuthentication(usuarioToken);
					
				} catch (Exception e) {
					System.out.println("Token inválido");
				}
			} else {
				System.out.println("Token inválido");
			}
		} else {
			System.out.println("Authorization não informado");
		}
		
		chain.doFilter(req, res);
	}

}
