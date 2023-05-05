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
import org.springframework.web.client.HttpClientErrorException.BadRequest;

@RestController
public class HabitosLoginControlador {

	Map<String, Cadastro> cadastroMap = new HashMap<String, Cadastro>();

	@PostMapping("/login")
	public ResponseEntity<Cadastro> login(@RequestBody LoginRequest loginRequest) {

		Cadastro usuario = cadastroMap.get(loginRequest.getUsuario());

		if (usuario != null && usuario.getSenha().equals(loginRequest.getSenha())) {

			return ResponseEntity.ok(usuario);

		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

	// no lugar de armazenar o id em uma variavel do tipo UUID, armazenei ela em uma
	// String "id", garante que cada usuario tenha seu id unico

	// Já que O meu response Entity retorna um objeto: criei uma classe de mensagem
	// de erro e criei um novo objeto para retornar o texto desejado
	// mudei O tipo do meu responseEntity para <?> Assim sendo compativel com todos
	// os objetos.
	
	// Padronizei um formato para o usuario usando os os metodos da classe String e
	// Regex pra limitar os caracteres que podem ser usados

	@PostMapping("/usuarios")
	public ResponseEntity<?> criarUsuario(@RequestBody Cadastro novoUsuario) {
		String usuario = novoUsuario.getUsuario().trim();

		if (!usuario.matches("^[A-za-z0-9_]+$")) {
			String mensagemErro = "Usuario invalido";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respostaErro);
		}
		int tamanhoMaximo = 8;
		int tamanhoMinimo = 4;
		if (usuario.length() < tamanhoMinimo || usuario.length() > tamanhoMaximo) {
			String mensagemErro = "O usuario deve ter no Min 4 e no Max 8 caracters";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mensagemErro);
		}

		boolean usuarioExitente = cadastroMap.values().stream()
				.anyMatch(cadastro -> cadastro.getUsuario().equals(novoUsuario.getUsuario()));
		if (usuarioExitente) {
			String mensagemErro = "usuario existente";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaErro);
		}else {

		String id = UUID.randomUUID().toString();
		novoUsuario.setId(id);

		cadastroMap.put(id, novoUsuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
		}
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
