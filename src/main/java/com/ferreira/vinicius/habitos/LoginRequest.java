package com.ferreira.vinicius.habitos;

import java.util.Objects;

public class LoginRequest {
	private String senha;
	private String usuario;

	public LoginRequest(String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginRequest other = (LoginRequest) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(usuario, other.usuario);
	}
	
	
	
}

