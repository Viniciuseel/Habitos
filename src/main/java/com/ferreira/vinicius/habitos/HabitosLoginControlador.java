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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitosLoginControlador {
	
	String email = "vinicius.arruda15es el@gmail.com";
	String senha = "12345";
	Map<String, Cadastro> cadastroMap = new  HashMap<String, Cadastro>();

	@PostMapping("/login")
	public String login(@RequestParam String usuario, @RequestParam String senha) {
		if (senha.equals(this.senha) && usuario.equals(email)){
			return " Acesso permitido" ;
		}
		else {
			return " Acesso negado" ;
		
		}	
	}
	
	@PostMapping("/usuario")
	public String salvarUsuario(@RequestParam String usuario, @RequestParam String senha) {
		Cadastro novoUsuario= new Cadastro();
	   novoUsuario.setSenha(senha);
	   novoUsuario.setUsuario(usuario);
	   cadastroMap.put(usuario,novoUsuario);
	   
		return "Usuario criado com sucesso.";
		
	}
	@GetMapping("/listarUsuario")
	public Collection<Cadastro> listarUsuarios() {
		List<Cadastro> cadastroList = new ArrayList<>();
		for (Entry<String, Cadastro> entry : cadastroMap.entrySet()) {
			Cadastro val = entry.getValue();
			cadastroList.add(val);
		    
		}
		return cadastroList;
	}
		
		@DeleteMapping("/deletarUsuario")
		public  String deletarUsuario (@RequestParam String nomeUsuario){
			
			 if(!cadastroMap.containsKey(nomeUsuario)) {
				 return "usuario inexistente";
			 }  else {
				 cadastroMap.remove(nomeUsuario);
			 return "usuario "+nomeUsuario+" removido com sucesso";
		}
			
	}
}
		
	


