package com.ferreira.vinicius.habitos;
//ORGANIZERI O CODIGO EM CAMADAS
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ferreira.vinicius.habitos.logica.LoginServico;

@SpringBootApplication
public class HabitosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitosApplication.class, args);
	}
	  @Bean
	    public LoginServico accessControlManager() {
	        return new LoginServico();
	    }
}
