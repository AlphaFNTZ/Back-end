package com.example.emprestimo_de_livros.dtos.response;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.models.LivroModel;
import com.example.emprestimo_de_livros.models.PessoaModel;

import java.util.Date;

public record LivroResponseDto(

        Long id_livro,
        String nome_livro,
        String nome_autor,
        Date data_lancamento
) {
    public LivroResponseDto(LivroModel livroModel){
        this(livroModel.getId_livro(), livroModel.getNome_livro(), livroModel.getNome_autor(), livroModel.getData_lancamento());
    }
}
