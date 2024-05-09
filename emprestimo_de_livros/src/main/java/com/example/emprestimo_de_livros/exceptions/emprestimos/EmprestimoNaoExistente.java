package com.example.emprestimo_de_livros.exceptions.emprestimos;

public class EmprestimoNaoExistente extends RuntimeException
{
    public EmprestimoNaoExistente(Long id)
    {
        super("O livro " + id + " já está emprestado para esta pessoa.");
    }
    public EmprestimoNaoExistente(Long id, int quantidade)
    {
        super("O livro " + id + " não está disponivel no momento");
    }
    public EmprestimoNaoExistente(Long id_livro, Long id_pessoa)
    {
        super("Não existe o emprestimo do livro " + id_livro + " para a pessoa " + id_pessoa);
    }
    public EmprestimoNaoExistente(Long id, String entidade)
    {
        super("O/A " + entidade + " " + id + " está com um emprestimo pendente");
    }
}