//Camada  de apresentação  com user

package com.ferreira.vinicius.habitos.apresentacao;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ferreira.vinicius.habitos.logica.LoginRequest;
import com.ferreira.vinicius.habitos.logica.LoginService;
import com.ferreira.vinicius.habitos.logica.Usuario;
import com.ferreira.vinicius.habitos.logica.UsuarioExistenteException;
import com.ferreira.vinicius.habitos.logica.UsuarioInvalidoException;
import com.ferreira.vinicius.habitos.logica.UsuarioServico;
import com.ferreira.vinicius.habitos.persistencia.UsuarioRepositorio;

@RestController
public class LoginControler {

	private LoginService loginService;
	private UsuarioServico usuarioServico;
	
	// 
	public LoginControler( ) {
		this.loginService = new LoginService();
		this.usuarioServico = new UsuarioServico( );
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
		boolean usuarioEstaAutenticado = loginService.login(loginRequest.getUsuario(), loginRequest.getSenha());
		if (usuarioEstaAutenticado) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	//Corrigi erro estava faltando a chave  do metodo 13/02
	@PostMapping("/usuarios")
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario novoUsuario) {
		try {
			this.usuarioServico.criarUsuario(novoUsuario);
			Usuario usuarioCriado = new Usuario();
			return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
		} catch (UsuarioInvalidoException | UsuarioExistenteException ex) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@GetMapping("/usuarios")
	public ResponseEntity<Collection<Usuario>> listarUsuarios() {
		Collection<Usuario> usuarios = this.usuarioServico.listarUsuarios();
		return ResponseEntity.ok(usuarios);
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable String id) {
		this.usuarioServico.deletarUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> atualizarCadastro(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
		try {
			this.usuarioServico.atualizarUsuario(id, usuarioAtualizado);
			return ResponseEntity.ok(usuarioAtualizado);
		} catch (UsuarioInvalidoException ex) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}
