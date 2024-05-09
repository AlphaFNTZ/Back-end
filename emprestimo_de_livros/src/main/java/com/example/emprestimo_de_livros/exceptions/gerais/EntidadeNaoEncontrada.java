package com.example.emprestimo_de_livros.exceptions.gerais;

import java.util.Optional;

public class EntidadeNaoEncontrada extends RuntimeException
{
    public EntidadeNaoEncontrada(Long id)
    {
        super("A entidade com o id " +id+ " não foi encontrada");
    }
    public EntidadeNaoEncontrada(String email)
    {
        super("O email '" +email+ "' não está cadastrado");
    }
    public EntidadeNaoEncontrada(Long id, String entidade)
    {
        super("O/A "+entidade+" com o id "+id+" não foi encontrada");
    }
}
