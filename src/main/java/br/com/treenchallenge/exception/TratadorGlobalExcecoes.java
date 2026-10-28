package br.com.treenchallenge.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestControllerAdvice
public class TratadorGlobalExcecoes {
@ExceptionHandler(RecursoNaoEncontradoException.class)
public ResponseEntity<Map<String, Object>> notFound(RuntimeException e) {
        return body(HttpStatus.NOT_FOUND, e.getMessage());
    }
@ExceptionHandler( {
        IllegalArgumentException.class, DataIntegrityViolationException.class
    }
    )
public ResponseEntity<Map<String, Object>> bad(RuntimeException e) {
        return body(HttpStatus.BAD_REQUEST, "Dados inválidos ou conflito de integridade.");
    }
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException e) {
        String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).findFirst().orElse("Dados inválidos.");
        return body(HttpStatus.BAD_REQUEST, m);
    }
    private ResponseEntity<Map<String, Object>> body(HttpStatus s, String m) {
        Map<String, Object>b=new LinkedHashMap<>();
        b.put("timestamp", LocalDateTime.now());
        b.put("status", s.value());
        b.put("erro", m);
        return ResponseEntity.status(s).body(b);
    }
}
