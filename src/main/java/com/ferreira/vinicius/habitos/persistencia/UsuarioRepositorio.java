package com.ferreira.vinicius.habitos.persistencia;

import java.util.HashMap;
import java.util.Map;

import com.ferreira.vinicius.habitos.logica.Usuario;

public class UsuarioRepositorio {
	private Map<String, Usuario> cadastroMap = new HashMap<>();

	public Map<String, Usuario> getCadastroMap() {
		return cadastroMap;
	}

	public void adicionarUsuario(Usuario usuario) {
		cadastroMap.put(usuario.getId(), usuario);
	}

	public boolean verificarNovoUsuario(Usuario usuarioCriado) {
		return cadastroMap.values()//
				.stream()//
				.anyMatch(usuario -> usuario.getUsuario()//
						.equals(usuarioCriado.getUsuario()));//
	}
}