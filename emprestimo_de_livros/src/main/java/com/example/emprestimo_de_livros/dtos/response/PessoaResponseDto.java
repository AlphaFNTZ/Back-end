package com.example.emprestimo_de_livros.dtos.response;

import com.example.emprestimo_de_livros.entities.PessoaModelo;

public record PessoaResponseDto(
        Long id_pessoa,
        String nome_pessoa,
        String email,
        String cep
)
{
    public PessoaResponseDto(PessoaModelo pessoaModelo)
    {
        this(pessoaModelo.getId_pessoa(), pessoaModelo.getNome_pessoa(), pessoaModelo.getEmail(), pessoaModelo.getCep());
    }
}
