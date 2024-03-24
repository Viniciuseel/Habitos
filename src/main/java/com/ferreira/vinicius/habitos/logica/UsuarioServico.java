package com.ferreira.vinicius.habitos.logica;

import java.util.Collection;
import java.util.UUID;

import com.ferreira.vinicius.habitos.persistencia.UsuarioRepositorio;

public class UsuarioServico {
	private UsuarioRepositorio usuarioRepositorio;
	private static final int TAMANHO_MINIMO_USUARIO = 4;
	private static final int TAMANHO_MAXIMO_USUARIO = 8;

	public UsuarioServico() {
		this.usuarioRepositorio = new UsuarioRepositorio();
	}

	public Usuario criarUsuario(Usuario novoUsuario) throws UsuarioInvalidoException, UsuarioExistenteException {

		if (!novoUsuario.getUsuario().matches("[A-za-z0-9_]+$")
				&& novoUsuario.getUsuario().length() < TAMANHO_MINIMO_USUARIO
				|| novoUsuario.getUsuario().length() > TAMANHO_MAXIMO_USUARIO) {
			throw new UsuarioInvalidoException("Usuario invalido");
		}

		/*if (novoUsuario.getSenha().length() < 8 || !novoUsuario.getSenha()
				.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])[a-zA-Z0-9@#$%^&+=]{8,}\"")) {
			throw new UsuarioInvalidoException(
					"A senha deve ter pelo menos 8 caracteres, uma letra minúscula, uma letra maiúscula, "
							+ "um número e um símbolo especial\"");
		}*/
		if (usuarioRepositorio.verificarNovoUsuario(novoUsuario)) {
			throw new UsuarioInvalidoException("Usuario já existe");
		}
//R
		Usuario usuarioCriado = new Usuario();
		String usuario = novoUsuario.getUsuario().trim();
		String senha = novoUsuario.getSenha().trim();
		String id = UUID.randomUUID().toString();
		usuarioCriado.setId(id);
		usuarioCriado.setUsuario(novoUsuario.getUsuario());
		usuarioCriado.setSenha(novoUsuario.getSenha());
		return usuarioCriado;
	}

	// Atualizei o metodo para usar o usuarioRepositotio 13/02
	public Collection<Usuario> listarUsuarios() {
		return usuarioRepositorio.getCadastroMap().values();
	}

	// Atualizei o metodo para usar o usuarioRepositotio 13/02
	public void deletarUsuario(String id) {
		usuarioRepositorio.getCadastroMap().remove(id);
	}

	// Atualizei o metodo para usar o usuarioRepositotio 13/02
	public Usuario atualizarUsuario(String id, Usuario usuarioAtualizado) throws UsuarioInvalidoException {
		if (usuarioRepositorio.getCadastroMap().containsKey(id)) {
			Usuario usuarioExistente = usuarioRepositorio.getCadastroMap().get(id);
			usuarioExistente.setSenha(usuarioAtualizado.getSenha());
			usuarioExistente.setUsuario(usuarioAtualizado.getSenha());
			return usuarioExistente;
		} else {
			throw new UsuarioInvalidoException("Usuario não encontrado");
		}
	}
}
