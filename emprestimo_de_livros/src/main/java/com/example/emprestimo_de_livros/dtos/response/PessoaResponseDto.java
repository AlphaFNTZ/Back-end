package com.example.emprestimo_de_livros.dtos.response;

import com.example.emprestimo_de_livros.models.PessoaModel;

public record PessoaResponseDto(
        Long id_pessoa,
        String nome_pessoa,
        Character cep
) {
    public PessoaResponseDto(PessoaModel pessoaModel) {
        this(pessoaModel.getId_pessoa(), pessoaModel.getNome_pessoa(), pessoaModel.getCEP());
    }
}
