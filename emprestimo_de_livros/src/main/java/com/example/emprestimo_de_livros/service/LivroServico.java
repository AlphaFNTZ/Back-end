package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.exceptions.gerais.EntidadeNaoEncontrada;
import com.example.emprestimo_de_livros.entities.LivroModelo;
import com.example.emprestimo_de_livros.exceptions.emprestimos.EmprestimoNaoExistente;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroServico
{
    @Autowired
    private LivroRepositorio livroRepositorio;

    public List<LivroResponseDto> getAllLivros()
    {
        List<LivroModelo> livros = livroRepositorio.findAll();
        return livros.stream().map(LivroResponseDto::new).collect(Collectors.toList());
    }
    public LivroResponseDto getLivroById(Long id_livro)
    {
        LivroModelo livroModelo = getEntityById(id_livro);
        return new LivroResponseDto(livroModelo);
    }
    public LivroResponseDto createLivro(LivroRequestDto livroResponseDto)
    {
        LivroModelo livroModelo = new LivroModelo(livroResponseDto);
        livroRepositorio.save(livroModelo);
        return new LivroResponseDto(livroModelo);
        //throw new LivroExistencia(livroResponseDto.nome_livro(), livroResponseDto.nome_autor());
    }
    public LivroResponseDto updateLivro(Long id_livro, LivroRequestDto livroRequestDto)
    {
        LivroModelo livroModelo = getEntityById(id_livro);
        livroModelo.setNome_livro(livroRequestDto.nome_livro());
        livroModelo.setNome_autor(livroRequestDto.nome_autor());
        livroModelo.setData_lancamento(livroRequestDto.data_lancamento());
        livroModelo.setQuantidade(livroRequestDto.quantidade());
        livroRepositorio.save(livroModelo);
        return new LivroResponseDto(livroModelo);
    }
    @Transactional
    public String deleteLivro(Long id_livro)
    {
        LivroModelo livroModelo = getEntityById(id_livro);
        if(livroModelo.getPessoas().isEmpty()) {
            livroRepositorio.delete(livroModelo);
            return "O livro " + id_livro + " foi removido com sucesso";
        }else{
            throw new EmprestimoNaoExistente(id_livro, "livro");
        }
    }
    private LivroModelo getEntityById(Long id_livro)
    {
        return livroRepositorio.findById(id_livro).orElseThrow(()-> new EntidadeNaoEncontrada(id_livro));
    }
}
