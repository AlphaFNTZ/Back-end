package com.example.emprestimo_de_livros.exceptions.senha;

public class SenhaErrada extends RuntimeException
{
    public SenhaErrada(String senha)
    {
        super("A senha está incorreta");
    }
}
