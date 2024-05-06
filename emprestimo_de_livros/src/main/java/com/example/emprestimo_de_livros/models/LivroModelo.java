package com.example.emprestimo_de_livros.models;
import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TB_LIVRO")

public class LivroModelo
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_livro;
    @Column(name = "nome_livro", nullable = false, length = 45)
    private String nome_livro;
    @Column(name = "nome_autor", nullable = false, length = 45)
    private String nome_autor;
    @Column(name = "data_lancamento", nullable = false)
    private Date data_lancamento;
    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @ManyToMany(mappedBy = "livros")
    private Set<PessoaModelo> pessoas;

    @Builder
    public LivroModelo(LivroRequestDto livroRequestDto)
    {
        this.nome_livro = livroRequestDto.nome_livro();
        this.nome_autor = livroRequestDto.nome_autor();
        this.data_lancamento = livroRequestDto.data_lancamento();
        this.quantidade = livroRequestDto.quantidade();
    }
}
