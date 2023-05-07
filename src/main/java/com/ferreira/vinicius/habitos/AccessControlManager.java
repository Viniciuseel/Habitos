package com.ferreira.vinicius.habitos;

import java.util.HashMap;
import java.util.Map;

public class AccessControlManager {
	private Map<String, Long> tempoDeAcesso;

	public AccessControlManager() {
		this.tempoDeAcesso = new HashMap<>();
	}

	public void registradorTempo(String idUsuario) {
		tempoDeAcesso.put(idUsuario, System.currentTimeMillis());
	}

	public  boolean acessoPermitido(String idUsuario) {
		Long ultimoAcesso = tempoDeAcesso.get(idUsuario);
		if (ultimoAcesso != null) {
			long horaAtual = System.currentTimeMillis();
			long tempoDecorrido = horaAtual - ultimoAcesso;
			return tempoDecorrido <= 30 * 60 * 1000;
		}
		return false;
	}
}