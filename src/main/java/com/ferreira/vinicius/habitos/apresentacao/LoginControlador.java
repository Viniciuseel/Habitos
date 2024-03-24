//Camada  de apresentação  com user

package com.ferreira.vinicius.habitos.apresentacao;

import java.util.Collection;

import com.ferreira.vinicius.habitos.logica.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ferreira.vinicius.habitos.logica.LoginRequisicao;

@RestController
public class LoginControlador {

	private LoginServico loginService;
	private UsuarioServico usuarioServico;
	
	// 
	public LoginControlador( ) {
		this.loginService = new LoginServico();
		this.usuarioServico = new UsuarioServico( );
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequisicao loginRequisicao) {
		boolean usuarioEstaAutenticado = loginService.login(loginRequisicao.getUsuario(), loginRequisicao.getSenha());
		if (usuarioEstaAutenticado) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
