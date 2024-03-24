package com.ferreira.vinicius.habitos.apresentacao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ferreira.vinicius.habitos.apresentacao.LoginControlador;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ferreira.vinicius.habitos.logica.Usuario;

//como testar criação de login, criar os testes.
public class LoginControladorTest {


//	 @Test
//	    public void testLoginComCredenciaisValidas() {
//	        LoginControlador controladorDeLogin = new LoginControlador();
//	        Usuario usuario = new Usuario();
//	        usuario.setUsuario("Vinicius");
//	        usuario.setSenha("Vinicius@1");
//	        
//	        controladorDeLogin.setAccessControlManager(new LoginService());
//
//	        ResponseEntity<Usuario> resposta = controladorDeLogin.login(new LoginRequisicao("Vinicius", "Vinicius@1"));
//	        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//	        assertNotNull(resposta.getBody());
//
//	        assertTrue(controladorDeLogin.loginService.isTempoRegistrado("Vinicius"));
//	    }

}
