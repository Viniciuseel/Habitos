package com.ferreira.vinicius.habitos.persistencia;

import java.util.Map;

import com.ferreira.vinicius.habitos.logica.Usuario;

public class LoginRepositorio {
	private Map<String, Long> tempoDeAcesso;

	public Usuario procurarUsuario(String usuario) {
		// TODO Auto-generated method stub
		return null;
	}
	public void registrarUltimoAcessoAutorizado(String idUsuario) {
		tempoDeAcesso.put(idUsuario, System.currentTimeMillis());
	}
	public long AcessarUltimoAcessoAutorizado(String idUsuario) {
		return System.currentTimeMillis();
	}
}
