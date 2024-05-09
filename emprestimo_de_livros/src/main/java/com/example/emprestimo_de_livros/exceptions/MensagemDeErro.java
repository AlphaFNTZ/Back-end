package com.example.emprestimo_de_livros.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public record MensagemDeErro(
        HttpStatus status,
        String message,
        Date timestamp
)
{
    public MensagemDeErro(HttpStatus status, String message)
    {
        this(status, message, new Date());
    }
}
