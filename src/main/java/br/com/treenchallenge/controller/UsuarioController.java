package br.com.treenchallenge.controller;

import br.com.treenchallenge.model.Usuario;
import br.com.treenchallenge.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/usuarios")
 public class UsuarioController {
    private final UsuarioService s;
    public UsuarioController(UsuarioService s) {
        this.s=s;
    }
@GetMapping
public List<Usuario> listar() {
        return s.listar();
    }
@GetMapping("/{id}")
public Usuario buscar(@PathVariable Long id) {
        return s.buscar(id);
    }
@PostMapping
public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario u) {
        return ResponseEntity.status(HttpStatus.CREATED).body(s.criar(u));
    }
@PutMapping("/{id}")
public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario u) {
        return s.atualizar(id, u);
    }
@DeleteMapping("/{id}")
public ResponseEntity<Void> excluir(@PathVariable Long id) {
        s.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
