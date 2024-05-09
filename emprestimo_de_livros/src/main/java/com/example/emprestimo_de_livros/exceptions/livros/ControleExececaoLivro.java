package com.example.emprestimo_de_livros.exceptions.livros;

import com.example.emprestimo_de_livros.exceptions.MensagemDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControleExececaoLivro {

    @ExceptionHandler(EmprestimoNaoExistente.class)
    public ResponseEntity<MensagemDeErro> emprestimoNaoExistente(EmprestimoNaoExistente e)
    {
        MensagemDeErro mensagemDeErro = new MensagemDeErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(mensagemDeErro.status()).body(mensagemDeErro);
    }
}