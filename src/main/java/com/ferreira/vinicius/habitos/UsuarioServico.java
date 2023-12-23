package com.ferreira.vinicius.habitos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UsuarioServico {
	private Map<String, Usuario> cadastroMap = new HashMap<>();

	public Usuario criarUsuario(Usuario novoUsuario) throws UsuarioInvalidoException, UsuarioExistenteException {
		Usuario usuarioCriado = new Usuario();
		String usuario = novoUsuario.getUsuario().trim();
		String senha = novoUsuario.getSenha().trim();
		String id = UUID.randomUUID().toString(); 
		usuarioCriado.setId(id);
		usuarioCriado.setUsuario(novoUsuario.getUsuario());
		usuarioCriado.setSenha(novoUsuario.getSenha());
		

		int tamanhoMinimo = 4;
		int tamanhoMaximo = 8;
		if (!usuario.matches("[A-za-z0-9_]+$") && usuario.length() < tamanhoMinimo
				|| usuario.length() > tamanhoMaximo) {
			throw new UsuarioInvalidoException("Usuario invalido");
		}
		for (Usuario cadastro : cadastroMap.values()) {
			if (cadastro.getUsuario().equals(usuario)) {
				throw new UsuarioExistenteException("Usuário já existe");
			}
		}
		/*
		 * verificando se o usuario já existe usando uma expreção lambda, e função
		 * anonima, desisti de usar pq n entendi direito
		 * if(cadastroMap.values().stream().anyMatch(cadastro ->
		 * cadastro.getUsuario().equals(usuario))) { }
		 */

		if (senha.length() < 8 || !senha.matches
				("(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}\"")) {
			throw new UsuarioInvalidoException(
					"A senha deve ter pelo menos 8 caracteres, uma letra minúscula, uma letra maiúscula, "
					+ "um número e um símbolo especial\"");
		}
		cadastroMap.put(id, usuarioCriado);

		return usuarioCriado;
	}

	public Collection<Usuario> listarUsuarios() {
		return cadastroMap.values();
	}

	public void deletarUsuario(String id) {
		cadastroMap.remove(id);
	}

	public Usuario atualizarUsuario(String id, Usuario usuarioAtualizado) throws UsuarioInvalidoException {
		if (cadastroMap.containsKey(id)) {
			Usuario usuarioExistente = cadastroMap.get(id);
			usuarioExistente.setSenha(usuarioAtualizado.getSenha());
			usuarioExistente.setUsuario(usuarioAtualizado.getSenha());
			return usuarioExistente;
		} else {
			throw new UsuarioInvalidoException("Usuario não encontrado");
		}
	}
}
