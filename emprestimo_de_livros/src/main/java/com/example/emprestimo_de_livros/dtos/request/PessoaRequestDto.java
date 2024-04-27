package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDto(
        @NotBlank(message = "Name is required")
        String nome_pessoa,
        @NotBlank(message = "Cep is required")
        Character cep
) {
}
