package com.ferreira.vinicius.habitos;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {
	private Map<String, Usuario> cadastroMap = new HashMap<String, Usuario>();
	
	public Usuario procuraUsuario(String nomeDoUsuario) {
		return cadastroMap.get(nomeDoUsuario);
	}
}
