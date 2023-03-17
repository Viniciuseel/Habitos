package com.ferreira.vinicius.habitos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitosLoginControlador {
	String email = "vinicius.arruda15eel@gmail.com";
	String senha = "v12345";

	@PostMapping("/login")
	public String login(@RequestParam String usuario, @RequestParam String senha) {
		if (senha.equals(this.senha) && usuario.equals(email)){
			return " Acesso permitido" ;
		}
		else {
			return " Acesso negado" ;
		}
		
	}
	
	
		
	

}
