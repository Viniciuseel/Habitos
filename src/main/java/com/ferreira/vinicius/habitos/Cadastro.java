package com.ferreira.vinicius.habitos;

import java.util.Objects;

public class Cadastro {
	private String usuario = "";
	private String senha = "";
	private String id;

	// Construtor
	public Cadastro() {

	}

	public Cadastro(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;

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
		Cadastro other = (Cadastro) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(usuario, other.usuario);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public Object getloginRequest() {
		return null;
	}

}
