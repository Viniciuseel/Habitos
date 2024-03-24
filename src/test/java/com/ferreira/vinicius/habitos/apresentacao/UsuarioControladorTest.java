package com.ferreira.vinicius.habitos.apresentacao;

import com.ferreira.vinicius.habitos.logica.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControladorTest {

    @Test
    public void testCriarNovoUsuarioComValoresVazios() {
        UsuarioControlador usuarioControlador = new UsuarioControlador();
        Usuario usuario = new Usuario();
        ResponseEntity<?> resposta = usuarioControlador.criarUsuario(usuario);
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }

    @Test
    public void testCriarNovoUsuarioComEmailValido() {
        UsuarioControlador usuarioControlador = new UsuarioControlador();
        Usuario usuario = new Usuario();
        usuario.setUsuario("Vinicius");
        usuario.setSenha("Vinicius@1");
        ResponseEntity<?> resposta = usuarioControlador.criarUsuario(usuario);
        assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
        Usuario usuarioResposta = ((Usuario) resposta.getBody());
        assertEquals("Vinicius", usuarioResposta.getUsuario());
        assertEquals("Vinicius@1", usuarioResposta.getSenha());
    }

    @Test
    void listarUsuarios() {
    }

    @Test
    void deletarUsuario() {
    }

    @Test
    void atualizarCadastro() {
    }
}