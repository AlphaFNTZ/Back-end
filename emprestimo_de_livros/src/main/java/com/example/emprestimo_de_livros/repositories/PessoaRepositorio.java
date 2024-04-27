package com.example.emprestimo_de_livros.repositories;

import com.example.emprestimo_de_livros.models.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositorio extends JpaRepository<PessoaModel, Long> {

}
