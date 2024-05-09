package com.example.emprestimo_de_livros.exceptions.livros;

public class EmprestimoNaoExistente extends RuntimeException {

    public EmprestimoNaoExistente(Long id)
    {
        super("O livro " + id + " já está emprestado para essa pessoa.");
    }
    public EmprestimoNaoExistente(Long id, int quantidade)
    {
        super("O livro " + id + " não está disponivel no momento");
    }
}