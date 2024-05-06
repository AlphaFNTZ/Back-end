package com.example.emprestimo_de_livros.excecoes.gerais;

import com.example.emprestimo_de_livros.excecoes.MensagemDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControleExcecaoGeral
{
    @ExceptionHandler(EntidadeNaoEncontrada.class)
    private ResponseEntity<MensagemDeErro> entidadeNaoEncontrada(EntidadeNaoEncontrada exception)
    {
        MensagemDeErro mensagemDeErro = new MensagemDeErro(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(mensagemDeErro.status()).body(mensagemDeErro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<List<MensagemDeErro>> metodoDeArgumentoNaoValido (MethodArgumentNotValidException exception)
    {
        List<MensagemDeErro> erros = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new MensagemDeErro(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}
