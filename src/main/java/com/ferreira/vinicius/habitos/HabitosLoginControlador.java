//Camada  de apresentação  com user

package com.ferreira.vinicius.habitos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitosLoginControlador {

	String conectado = "1";
	Map<String, Cadastro> cadastroMap = new HashMap<String, Cadastro>();
//onde por a classe Resquest
	@PostMapping("/login")
	public ResponseEntity<Cadastro> login(@RequestBody LoginRequest loginRequest) {
		
		Cadastro usuario = cadastroMap.get(loginRequest.getUsuario());
		usuario.setUsuario(loginRequest.getUsuario());
		
		if (usuario!= null && usuario.getloginRequest().equals(loginRequest.getSenha())) {
			
			return ResponseEntity.ok().build();

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

//Por que não preciso criar um novo usuario?  ou aplicar
	@PostMapping("/usuarios")
	public ResponseEntity<Cadastro> criarUsuario(@RequestBody Cadastro novoUsuario) {
		UUID uuid = UUID.randomUUID();

		novoUsuario.setId(uuid.toString());

		cadastroMap.put(uuid.toString(), novoUsuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}

	@GetMapping("/usuarios")
	public Collection<Cadastro> listarUsuarios() {
		return cadastroMap.values();
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Cadastro> deletarUsuario(@PathVariable String id) {
		if (!cadastroMap.containsKey(id)) {
			return ResponseEntity.notFound().build();
		} else {
			cadastroMap.remove(id);
			return ResponseEntity.ok().build();
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Cadastro> atualizarCadastro(@PathVariable String id,
			@RequestBody Cadastro usuarioAtualizado) {
		Cadastro cadastro = cadastroMap.get(id);
		if (cadastro == null) {
			return ResponseEntity.notFound().build();
		}
		cadastro.setSenha(usuarioAtualizado.getSenha());
		cadastro.setUsuario(usuarioAtualizado.getUsuario());
		return ResponseEntity.ok(cadastro);
	}
}
