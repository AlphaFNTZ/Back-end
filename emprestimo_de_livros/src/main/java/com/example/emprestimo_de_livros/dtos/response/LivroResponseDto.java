package com.example.emprestimo_de_livros.dtos.response;

import com.example.emprestimo_de_livros.models.LivroModelo;
import java.util.Date;

public record LivroResponseDto(
        Long id_livro,
        String nome_livro,
        String nome_autor,
        Date data_lancamento,
        int quantidade
)
{
    public LivroResponseDto(LivroModelo livroModelo)
    {
        this(livroModelo.getId_livro(), livroModelo.getNome_livro(), livroModelo.getNome_autor(), livroModelo.getData_lancamento(), livroModelo.getQuantidade());
    }
}
