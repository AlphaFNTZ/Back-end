package com.example.emprestimo_de_livros.models;

import com.example.emprestimo_de_livros.dtos.request.PessoaRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TB_PESSOA")

public class PessoaModelo
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_pessoa;
    @Column(name = "nome_pessoa", nullable = false, length = 45)
    private String nome_pessoa;
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="tb_emprestimo",
            joinColumns = @JoinColumn(name="id_pessoa"),
            inverseJoinColumns = @JoinColumn(name="id_Livro")
    )
    private Set<LivroModelo> livros;

    @Builder
    public PessoaModelo(PessoaRequestDto pessoaRequestDto)
    {
        this.nome_pessoa = pessoaRequestDto.nome_pessoa();
        this.cep = pessoaRequestDto.cep();
    }
}
