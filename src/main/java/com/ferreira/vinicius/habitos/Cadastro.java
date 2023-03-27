package com.ferreira.vinicius.habitos;
import java.util.Map;
import java.util.Objects;
import java.util.HashMap;
public class Cadastro implements java.io.Serializable {
	private String usuario = "";
	private String senha = "";
	//Construtor
	public Cadastro() {
		
	}
	
	public Cadastro(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	public String getUsuario(){
		return this.usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario; 
	}
	public String getSenha(){
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
	
	
	
}
