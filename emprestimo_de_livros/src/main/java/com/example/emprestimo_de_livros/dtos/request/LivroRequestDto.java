package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record LivroRequestDto(
        @NotBlank(message = "O nome do livro é necessario")
        String nome_livro,
        @NotBlank(message = "O nome do autor é necessario")
        String nome_autor,
        @NotBlank(message = "A data de lançamento é necessario")
        Date data_lancamento,
        @NotBlank(message = "É necessario uma quantidade de livros inicial")
        int quantidade
) {
}
