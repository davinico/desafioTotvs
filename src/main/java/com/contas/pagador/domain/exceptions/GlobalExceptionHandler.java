package com.contas.pagador.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handleContaNaoEncontradaException(ContaNaoEncontradaException ex) {
        Map<String, Object> error = Map.of("timestamp", LocalDateTime.now(), "status", 422, "error", "Id nao encontrado", "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error);
    }

    @ExceptionHandler(ContaJaPagaException.class)
    public ResponseEntity<Map<String, Object>> handleContaJaPagaException(ContaJaPagaException ex) {
        Map<String, Object> error = Map.of("timestamp", LocalDateTime.now(), "status", 422, "error", "Conta ja foi paga", "message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(error);
    }
}
