package com.example.emprestimo_de_livros.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_EMPRESTIMO")

public class EmprestimoModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_livro")
    private LivroModel id_livro;

    @ManyToOne()
    @JoinColumn(name = "id_pessoa")
    private PessoaModel id_pessoa;
}
