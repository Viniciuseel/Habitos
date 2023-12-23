package com.ferreira.vinicius.habitos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginControlerTest {

	@Test
	public void testCriarNovoUsuarioComValoresVazios() {
		LoginControler controladorDeLogin = new LoginControler();
		Usuario usuario = new Usuario();
		ResponseEntity<?> resposta = controladorDeLogin.criarUsuario(usuario);
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
	}
	
	@Test
	 public void testCriarNovoUsuarioComEmailValido() {
		LoginControler controladorDeLogin = new LoginControler();
		Usuario usuario = new Usuario();
		usuario.setUsuario("Vinicius");
		usuario.setSenha("Vinicius@1");
		ResponseEntity<?> resposta = controladorDeLogin.criarUsuario(usuario);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		Usuario usuarioResposta = ((Usuario)resposta.getBody());
		assertEquals("Vinicius", usuarioResposta.getUsuario());
		assertEquals("Vinicius@1", usuarioResposta.getSenha());
	}
//	 @Test
//	    public void testLoginComCredenciaisValidas() {
//	        LoginControler controladorDeLogin = new LoginControler();
//	        Usuario usuario = new Usuario();
//	        usuario.setUsuario("Vinicius");
//	        usuario.setSenha("Vinicius@1");
//	        
//	        controladorDeLogin.setAccessControlManager(new LoginService());
//
//	        ResponseEntity<Usuario> resposta = controladorDeLogin.login(new LoginRequest("Vinicius", "Vinicius@1"));
//	        assertEquals(HttpStatus.OK, resposta.getStatusCode());
//	        assertNotNull(resposta.getBody());
//
//	        assertTrue(controladorDeLogin.loginService.isTempoRegistrado("Vinicius"));
//	    }

	
	
	
		
	
	
	

}
