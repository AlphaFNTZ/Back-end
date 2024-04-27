package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDto(
        @NotBlank(message = "O nome da pessoa é necessario")
        String nome_pessoa,
        @NotBlank(message = "O CEP é necessario")
        String cep
) {
}
