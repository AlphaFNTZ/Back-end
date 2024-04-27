package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record LivroRequestDto(
        @NotBlank(message = "Name is required")
        String nome_livro,
        @NotBlank(message = "Name is required")
        String nome_autor,
        Date data_lancamento
) {
}
