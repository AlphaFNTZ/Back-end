package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.request.PessoaRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.models.LivroModel;
import com.example.emprestimo_de_livros.models.PessoaModel;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    public List<PessoaResponseDto> getAllPessoa(){
        List<PessoaModel> pessoas = pessoaRepositorio.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }
    public PessoaResponseDto getPessoaById(Long id_pessoa){
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        return new PessoaResponseDto(pessoaModel);
    }
    public PessoaResponseDto createPessoa(PessoaRequestDto pessoaResponseDto){
        PessoaModel pessoaModel = new PessoaModel(pessoaResponseDto);
        pessoaRepositorio.save(pessoaModel);
        return new PessoaResponseDto(pessoaModel);
    }
    public PessoaResponseDto updatePessoa(Long id_pessoa, PessoaRequestDto pessoaRequestDto){
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        pessoaModel.setNome_pessoa(pessoaRequestDto.nome_pessoa());
        pessoaModel.setCEP(pessoaRequestDto.cep());
        pessoaRepositorio.save(pessoaModel);
        return new PessoaResponseDto(pessoaModel);
    }
    public String deletePessoa(Long id_pessoa){
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        pessoaRepositorio.delete(pessoaModel);
        return "Pessoa removida com sucesso";
    }
    private PessoaModel getEntityById(Long id_pessoa){
        return pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new RuntimeException("Pessoa nao encontrada"));
    }
}
