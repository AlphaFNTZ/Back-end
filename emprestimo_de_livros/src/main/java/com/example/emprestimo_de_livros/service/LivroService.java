package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.models.LivroModel;
import com.example.emprestimo_de_livros.models.PessoaModel;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    @Autowired
    private LivroRepositorio livroRepositorio;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;

    public List<LivroResponseDto> getAllLivros(){
        List<LivroModel> livros = livroRepositorio.findAll();
        return livros.stream().map(LivroResponseDto::new).collect(Collectors.toList());
    }
    public LivroResponseDto getLivroById(Long id_livro){
        LivroModel livroModel = getEntityById(id_livro);
        return new LivroResponseDto(livroModel);
    }
    public LivroResponseDto createLivro(LivroRequestDto livroResponseDto){
        LivroModel livroModel = new LivroModel(livroResponseDto);
        livroRepositorio.save(livroModel);
        return new LivroResponseDto(livroModel);
    }
    public LivroResponseDto updateLivro(Long id_livro, LivroRequestDto livroRequestDto){
        LivroModel livroModel = getEntityById(id_livro);
        livroModel.setNome_livro(livroRequestDto.nome_livro());
        livroModel.setNome_autor(livroRequestDto.nome_autor());
        livroModel.setData_lancamento(livroRequestDto.data_lancamento());
        livroRepositorio.save(livroModel);
        return new LivroResponseDto(livroModel);
    }
    public String deleteLivro(Long id_livro){
        LivroModel livroModel = getEntityById(id_livro);
        livroRepositorio.delete(livroModel);
        return "Livro removido com sucesso";
    }
    private LivroModel getEntityById(Long id_livro){
        return livroRepositorio.findById(id_livro).orElseThrow(()-> new RuntimeException("Livro não encontrado"));
    }
}
