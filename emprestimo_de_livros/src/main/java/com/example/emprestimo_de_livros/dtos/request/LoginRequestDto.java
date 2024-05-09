package com.example.emprestimo_de_livros.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @NotBlank(message = "O e-mail é necessario")
        @Size(min = 1, max = 45, message = "O E-mail não contém o tamanho adequado")
        String email,
        @NotBlank(message = "A senha é necessaria")
        @Size(min = 8, max = 12, message = "A senha não contém o tamanho adequado")
        String senha
)
{
}
