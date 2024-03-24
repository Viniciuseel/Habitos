package com.ferreira.vinicius.habitos.apresentacao;

import com.ferreira.vinicius.habitos.logica.Usuario;
import com.ferreira.vinicius.habitos.logica.UsuarioExistenteException;
import com.ferreira.vinicius.habitos.logica.UsuarioInvalidoException;
import com.ferreira.vinicius.habitos.logica.UsuarioServico;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UsuarioControlador {
    private UsuarioServico usuarioServico;
    public  UsuarioControlador(){
        this.usuarioServico = new UsuarioServico();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario novoUsuario) {
        try {
            this.usuarioServico.criarUsuario(novoUsuario);
            Usuario usuarioCriado = new Usuario();
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (UsuarioInvalidoException | UsuarioExistenteException ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Collection<Usuario>> listarUsuarios() {
        Collection<Usuario> usuarios = this.usuarioServico.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable String id) {
        this.usuarioServico.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> atualizarCadastro(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        try {
            this.usuarioServico.atualizarUsuario(id, usuarioAtualizado);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (UsuarioInvalidoException ex) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}