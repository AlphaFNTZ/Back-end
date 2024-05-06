package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.PessoaRequestDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.models.LivroModel;
import com.example.emprestimo_de_livros.models.PessoaModel;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService
{
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private LivroRepositorio livroRepositorio;

    public List<PessoaResponseDto> getAllPessoa()
    {
        List<PessoaModel> pessoas = pessoaRepositorio.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }
    public PessoaResponseDto getPessoaById(Long id_pessoa)
    {
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        return new PessoaResponseDto(pessoaModel);
    }
    public PessoaResponseDto createPessoa(PessoaRequestDto pessoaResponseDto)
    {
        PessoaModel pessoaModel = new PessoaModel(pessoaResponseDto);
        pessoaRepositorio.save(pessoaModel);
        return new PessoaResponseDto(pessoaModel);
    }
    public PessoaResponseDto updatePessoa(Long id_pessoa, PessoaRequestDto pessoaRequestDto)
    {
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        pessoaModel.setNome_pessoa(pessoaRequestDto.nome_pessoa());
        pessoaModel.setCep(pessoaRequestDto.cep());
        pessoaRepositorio.save(pessoaModel);
        return new PessoaResponseDto(pessoaModel);
    }
    public String deletePessoa(Long id_pessoa)
    {
        PessoaModel pessoaModel = getEntityById(id_pessoa);
        pessoaRepositorio.delete(pessoaModel);
        return "Pessoa removida com sucesso";
    }
    private PessoaModel getEntityById(Long id_pessoa)
    {
        return pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new RuntimeException("Pessoa nao encontrada"));
    }
    @Transactional
    public String emprestimoLivro(Long id_pessoa, long id_livro){
        if(pessoaRepositorio.existsById(id_pessoa) && livroRepositorio.existsById(id_livro)){
            LivroModel livro = livroRepositorio.findById(id_livro).get();
            if(livro.getQuantidade()>0) {
                PessoaModel pessoa = getEntityById(id_pessoa);
                pessoa.getLivros().add(livro);
                int quantidade = livro.getQuantidade();
                livro.setQuantidade(quantidade-1);
                pessoaRepositorio.save(pessoa);
                return "O emprestimo do livro foi efetuado com sucesso";
            }else
            {
                return "O livro não está disponivel no momento";
            }
        }else{
            return "O livro ou a pessoa do id respectivo não está cadastrada";
        }
    }
    @Transactional
    public String devolverLivro(Long id_pessoa, long id_livro){
        if(pessoaRepositorio.existsById(id_pessoa) && livroRepositorio.existsById(id_livro)){
            PessoaModel pessoa = getEntityById(id_pessoa);
            LivroModel livro = livroRepositorio.findById(id_livro).get();
            pessoa.getLivros().remove(livro);
            int quantidade = livro.getQuantidade();
            livro.setQuantidade(quantidade+1);
            pessoaRepositorio.save(pessoa);
            return "A devolução do livro foi efetuada com sucesso";
        }else{
            return "O livro ou a pessoa do id respectivo não está cadastrada";
        }
    }
}
