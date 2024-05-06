package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaRequestDto(
        @NotBlank(message = "O nome da pessoa é necessario")
        @Size(min = 1, max = 45, message = "O nome não contém o tamanho adequado")
        String nome_pessoa,
        @NotBlank(message = "O numero de CEP é necessario")
        @Size(min = 1, max = 9, message = "O CEP não contém o tamanho adequado")
        String cep
)
{
}
