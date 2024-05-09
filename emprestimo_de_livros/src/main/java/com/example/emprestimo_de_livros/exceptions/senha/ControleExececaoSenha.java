package com.example.emprestimo_de_livros.exceptions.senha;

import com.example.emprestimo_de_livros.exceptions.MensagemDeErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControleExececaoSenha
{
    @ExceptionHandler(SenhaErrada.class)
    public ResponseEntity<MensagemDeErro> senhaErrada(SenhaErrada e)
    {
        MensagemDeErro mensagemDeErro = new MensagemDeErro(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(mensagemDeErro.status()).body(mensagemDeErro);
    }
}
