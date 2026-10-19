package br.com.treenchallenge.controller;
import br.com.treenchallenge.model.AcaoSustentavel;
import br.com.treenchallenge.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api/acoes")
public class AcaoSustentavelController{
    private final AcaoSustentavelService s;
    public AcaoSustentavelController(AcaoSustentavelService s){this.s=s;}@GetMapping 
    public List<AcaoSustentavel> listar(){return s.listar();}@GetMapping("/{id}")
    public AcaoSustentavel buscar(@PathVariable Long id){return s.buscar(id);}@GetMapping("/usuario/{usuarioId}")
    public List<AcaoSustentavel> porUsuario(@PathVariable Long usuarioId){
        return s.porUsuario(usuarioId);}@PostMapping 
    public ResponseEntity<AcaoSustentavel> criar(@Valid @RequestBody AcaoSustentavel a,@RequestParam Long usuarioId){
        return ResponseEntity.status(HttpStatus.CREATED).body(s.criar(a,usuarioId));}
        @PutMapping("/{id}")
        public AcaoSustentavel atualizar(@PathVariable Long id,@Valid @RequestBody AcaoSustentavel a,@RequestParam Long usuarioId){
            return s.atualizar(id,a,usuarioId);}@DeleteMapping("/{id}")
            public ResponseEntity<Void> excluir(@PathVariable Long id){s.excluir(id);return ResponseEntity.noContent().build();}}
