package br.senai.sp.info.pweb.keeper.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.info.pweb.keeper.models.Usuario;

public class JwtUtils {

	public static String gerarToken(Usuario usuario) 
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		
		return JWT.create()
			.withIssuer("Victor Issa - Keeper").withIssuedAt(new Date())
			.withSubject("Autenticação")
			.withClaim("id", usuario.getId()).withClaim("nome", usuario.getNome())
			.sign(Algorithm.HMAC512("1D3C977E8C8367731AAAC7BEC764D583AE670A1EFAE9581337A3628E5E1FD2CC2"
					+ "2FCC48FBEA683A5DE43337DE838FD1DA8D1E0A8DAAE6FF5C8A32DF9F753A0F9"));
	}
	
	public static Usuario extrairUsuarioToken(String token) {
		Usuario usuario = new Usuario();
		
		DecodedJWT jwtDecodificado = JWT.decode(token);
		usuario.setId(jwtDecodificado.getClaim("id").asLong());
		usuario.setNome(jwtDecodificado.getClaim("nome").asString());
		
		return usuario;
	}
	
	public static void certificarToken(String token) throws JWTVerificationException, 
		IllegalArgumentException, UnsupportedEncodingException {
		
		JWT.require(Algorithm.HMAC512("1D3C977E8C8367731AAAC7BEC764D583AE670A1EFAE9581337A3628E5E1FD2CC2"
				+ "2FCC48FBEA683A5DE43337DE838FD1DA8D1E0A8DAAE6FF5C8A32DF9F753A0F9")).build().verify(token); 
	}
	
}
