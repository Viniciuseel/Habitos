package com.ferreira.vinicius.habitos;
//ORGANIZERI O CODIGO EM CAMADAS
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ferreira.vinicius.habitos.logica.LoginService;

@SpringBootApplication
public class HabitosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitosApplication.class, args);
	}
	  @Bean
	    public LoginService accessControlManager() {
	        return new LoginService();
	    }
}
