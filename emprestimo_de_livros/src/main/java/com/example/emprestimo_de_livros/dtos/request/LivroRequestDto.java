package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record LivroRequestDto(
        @NotBlank(message = "O nome do livro é necessario")
        @Size(min = 0, max = 45, message = "O nome não contém o tamanho adequado")
        String nome_livro,
        @NotBlank(message = "O nome do autor é necessario")
        @Size(min = 0, max = 45, message = "O nome não contém o tamanho adequado")
        String nome_autor,
        @NotNull(message = "A data de lançamento é necessaria")
        Date data_lancamento,
        @Min(value = 1, message = "É necessario uma quantidade inicial de livros")
        int quantidade
)
{
}
