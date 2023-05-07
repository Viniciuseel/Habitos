package com.ferreira.vinicius.habitos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HabitosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitosApplication.class, args);
	}
	  @Bean
	    public AccessControlManager accessControlManager() {
	        return new AccessControlManager();
	    }
}
