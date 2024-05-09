package com.example.emprestimo_de_livros.exceptions.gerais;

import java.util.Optional;

public class EntidadeNaoEncontrada extends RuntimeException
{
    public EntidadeNaoEncontrada(Long id)
    {
        super("A entidade com o id '" +id+ "' não foi encontrada");
    }
    public EntidadeNaoEncontrada(String entidade)
    {
        super("A entidade com a seguinte informação '" + entidade + "' não está cadastrada");
    }
    public EntidadeNaoEncontrada(Long id, String entidade)
    {
        super("O/A " + entidade + " com o id " + id + " não foi encontrado(a)");
    }
}
