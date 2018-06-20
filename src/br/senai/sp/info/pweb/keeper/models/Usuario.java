package br.senai.sp.info.pweb.keeper.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="usuario")

public class Usuario implements Authentication{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private TiposUsuario tipo=TiposUsuario.COMUM;
	
	@Column(length=20, nullable=false, unique=false)
	@Size(min=1, max=20)
	@NotNull
	private String nome;
	
	@Column(length=40, nullable=false, unique=false)
	@Size(min=1, max=40)
	@NotNull
	private String sobrenome;
	
	@Column(length=120, nullable=false, unique=true)
	@Size(min=3, max=120)
	@NotNull
	@Email
	private String email;
	
	@Column(length=64, nullable=false, unique=false)
	@Size(min=5, max=64)
	@NotNull
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TiposUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TiposUsuario tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void hashearSenha() {
		this.senha=DigestUtils.md5DigestAsHex(this.senha.getBytes());
	}
	
	public boolean getAdministrador() {
		return this.tipo.equals(TiposUsuario.ADMINISTRADOR);
	}

	@Override
	public String getName() {
		return nome;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	@JsonIgnore 
	public Object getPrincipal() {
		return this;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean arg0) throws IllegalArgumentException {
	}
}
