package com.ferreira.vinicius.habitos;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BooleanSupplier;

public class LoginService {

	private LoginRepository loginRepository;

	// private Map<String, Usuario> cadastroMap = new HashMap<String, Usuario>();
	// private Map<String, Long> tempoDeAcesso;
	public LoginService( ) {
		this.loginRepository = new LoginRepository();
	}
	
	public void adicionarUsuario(String usuario, String senha) {
		Usuario novoUsuario = new Usuario(usuario, senha);
		cadastroMap.put(usuario, novoUsuario);
	}

	public Usuario getUsuario(String nomeDoUsuario) {
		return cadastroMap.get(nomeDoUsuario);
	}

	public boolean login(String usuario, String senha) {
		Usuario usuarioObj = loginRepository.procuraUsuario(usuario);

		if (usuario != null && usuarioObj.getSenha().equals(senha)) {
			this.registradorTempo(usuario);
			return this.acessoPermitido(usuario);

		} else {
			return false;
		}
	}

	private void registradorTempo(String idUsuario) {
		tempoDeAcesso.put(idUsuario, System.currentTimeMillis());
	}

	private boolean acessoPermitido(String idUsuario) {
		Long ultimoAcesso = tempoDeAcesso.get(idUsuario);
		if (ultimoAcesso != null) {
			long horaAtual = System.currentTimeMillis();
			long tempoDecorrido = horaAtual - ultimoAcesso;
			return tempoDecorrido <= 30 * 60 * 1000;
		}
		return false;
	}

	private BooleanSupplier isTempoRegistrado(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}