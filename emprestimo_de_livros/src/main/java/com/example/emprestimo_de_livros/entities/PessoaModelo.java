package com.example.emprestimo_de_livros.entities;

import com.example.emprestimo_de_livros.dtos.request.RegistroRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Column(name = "senha", nullable = false, length = 80)
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="tb_emprestimo",
            joinColumns = @JoinColumn(name="id_pessoa"),
            inverseJoinColumns = @JoinColumn(name="id_Livro")
    )
    private List<LivroModelo> livros;

    @Builder
    public PessoaModelo(RegistroRequestDto registroRequestDto)
    {
        this.nome_pessoa = registroRequestDto.nome_pessoa();
        this.cep = registroRequestDto.cep();
    }
}
