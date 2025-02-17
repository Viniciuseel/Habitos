package com.ferreira.vinicius.habitos.logica;

import java.util.Objects;

public class LoginRequisicao {
	private String senha;
	private String usuario;

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
		LoginRequisicao other = (LoginRequisicao) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(usuario, other.usuario);
	}

}
