package com.example.emprestimo_de_livros.repositories;

import com.example.emprestimo_de_livros.models.PessoaModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepositorio extends JpaRepository<PessoaModelo, Long>
{
}
