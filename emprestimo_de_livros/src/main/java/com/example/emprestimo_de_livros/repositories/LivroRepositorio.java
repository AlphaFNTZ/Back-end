package com.example.emprestimo_de_livros.repositories;

import com.example.emprestimo_de_livros.entities.LivroModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepositorio extends JpaRepository<LivroModelo, Long>
{

}
