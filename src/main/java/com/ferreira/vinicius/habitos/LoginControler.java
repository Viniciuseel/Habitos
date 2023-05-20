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
public class LoginControler {

	Map<String, Usuario> cadastroMap = new HashMap<String, Usuario>();
	AccessControlManager accessControlManager;

	public LoginControler() {
		this.accessControlManager = new AccessControlManager();
	}

	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody LoginRequest loginRequest) {

		Usuario usuario = cadastroMap.get(loginRequest.getUsuario());

		 if (usuario != null && usuario.getSenha().equals(loginRequest.getSenha())) {
		        this.accessControlManager.registradorTempo(loginRequest.getUsuario());
		        if (this.accessControlManager.acessoPermitido(loginRequest.getUsuario())) {
		            return ResponseEntity.ok(usuario);
		        } else {
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		        }
		    } else {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		    }
		}

	@PostMapping("/usuarios")
	public ResponseEntity<?> criarUsuario(@RequestBody Usuario novoUsuario) {
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
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
		}

		boolean usuarioExitente = cadastroMap.values().stream()
				.anyMatch(cadastro -> cadastro.getUsuario().equals(novoUsuario.getUsuario()));
		if (usuarioExitente) {
			String mensagemErro = "usuario existente";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(respostaErro);
		}
		// padronizei o formato da senha
		String senha = novoUsuario.getSenha().trim();

		if (senha.length() < 8 && !senha.matches("^[\\w-\\.]+@[a-zA-Z0-9-]+\\.[a-z]{2,4}")) {
			String mensagemErro = "A senha deve ter pelo menos 8 caracteres, uma letra minúscula, uma letra maiúscula, um número e um símbolo especial";
			return responderComErro(HttpStatus.UNPROCESSABLE_ENTITY,mensagemErro);
		}

		String id = UUID.randomUUID().toString();
		novoUsuario.setId(id);

		cadastroMap.put(id, novoUsuario);

		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);

	}

	private ResponseEntity<?> responderComErro(HttpStatus statusDaRespostaHttp, String mensagemErro) {
		RespostaErro respostaErro = new RespostaErro(mensagemErro);
		return ResponseEntity.status(statusDaRespostaHttp).body(respostaErro);
	}

	@GetMapping("/usuarios")
	public Collection<Usuario> listarUsuarios() {
		return cadastroMap.values();
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable String id) {
		if (!cadastroMap.containsKey(id)) {
			String mensagemErro = "Usuario não encontrado";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respostaErro);
		} else {
			cadastroMap.remove(id);
			String mensagemErro = "Usuario removido";
			RespostaErro respostaErro = new RespostaErro(mensagemErro);
			return ResponseEntity.status(HttpStatus.OK).body(respostaErro);
		}
	}

	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> atualizarCadastro(@PathVariable String id,
			@RequestBody Usuario usuarioAtualizado) {
		Usuario cadastro = cadastroMap.get(id);

		if (cadastro == null) {
			return ResponseEntity.notFound().build();
		}
		cadastro.setSenha(usuarioAtualizado.getSenha());
		cadastro.setUsuario(usuarioAtualizado.getUsuario());
		return ResponseEntity.ok(cadastro);
	}
}