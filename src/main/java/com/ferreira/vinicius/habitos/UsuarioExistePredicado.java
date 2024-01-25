package com.ferreira.vinicius.habitos;

import java.util.function.Predicate;

public class UsuarioExistePredicado implements Predicate<Usuario> {
	private Usuario usuarioCriado;
	
	public  UsuarioExistePredicado(Usuario usuarioCriado) {
		this.usuarioCriado = usuarioCriado;
	}
	
	@Override
	public boolean test(Usuario usuarioExistente) {
		return usuarioExistente.getUsuario().equals(usuarioCriado.getUsuario());	
		
		
	}
	

}
