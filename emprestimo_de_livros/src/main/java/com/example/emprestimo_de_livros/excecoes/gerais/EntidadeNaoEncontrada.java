package com.example.emprestimo_de_livros.excecoes.gerais;


public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(Long id) {
        super("A entidade com o id " +id+ " não foi encontrada");
    }
}
