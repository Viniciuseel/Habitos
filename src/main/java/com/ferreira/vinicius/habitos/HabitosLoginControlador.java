//Camada  de apresentação  com user

package com.ferreira.vinicius.habitos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitosLoginControlador {

	

	Map<String, Cadastro> cadastroMap = new HashMap<String, Cadastro>();

	@PostMapping("/login")
	public String login(@RequestParam String usuario, @RequestParam String senha) {
		
		if (cadastroMap.containsKey(usuario) && cadastroMap.get(usuario).getSenha().equals(senha)) {
			return " Acesso permitido";
		} else if(!cadastroMap.containsKey(usuario)) {
			return "Usuario inexistente";
		}
		else {
			return "Acesso negado";
		}
	}

	@PostMapping("/usuario")
	public String salvarUsuario(@RequestParam String usuario, @RequestParam String senha) {
		Cadastro novoUsuario = new Cadastro();
		novoUsuario.setSenha(senha);
		novoUsuario.setUsuario(usuario);
		cadastroMap.put(usuario, novoUsuario);

		return "Usuario criado com sucesso.";

	}

	@GetMapping("/listarUsuario")
	public Collection<Cadastro> listarUsuarios() {
	return cadastroMap.values();

		}
	
	@DeleteMapping("/deletarUsuario")
	public String deletarUsuario(@RequestParam String nomeUsuario) {

		if (!cadastroMap.containsKey(nomeUsuario)) {
			return "usuario inexistente";
		} else {
			cadastroMap.remove(nomeUsuario);
			return "usuario " + nomeUsuario + " removido com sucesso";
		}
	}
	@PostMapping("/atualizarUsuario")
	public Collection<Cadastro> atualizarUsuario(){
		return null;
	}
}
