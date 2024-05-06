package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.PessoaRequestDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.excecoes.gerais.EntidadeNaoEncontrada;
import com.example.emprestimo_de_livros.models.LivroModelo;
import com.example.emprestimo_de_livros.models.PessoaModelo;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServico
{
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private LivroRepositorio livroRepositorio;

    public List<PessoaResponseDto> getAllPessoa()
    {
        List<PessoaModelo> pessoas = pessoaRepositorio.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }
    public PessoaResponseDto getPessoaById(Long id_pessoa)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        return new PessoaResponseDto(pessoaModelo);
    }
    public PessoaResponseDto createPessoa(PessoaRequestDto pessoaResponseDto)
    {
        PessoaModelo pessoaModelo = new PessoaModelo(pessoaResponseDto);
        pessoaRepositorio.save(pessoaModelo);
        return new PessoaResponseDto(pessoaModelo);
    }
    public PessoaResponseDto updatePessoa(Long id_pessoa, PessoaRequestDto pessoaRequestDto)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        pessoaModelo.setNome_pessoa(pessoaRequestDto.nome_pessoa());
        pessoaModelo.setCep(pessoaRequestDto.cep());
        pessoaRepositorio.save(pessoaModelo);
        return new PessoaResponseDto(pessoaModelo);
    }
    public String deletePessoa(Long id_pessoa)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        pessoaRepositorio.delete(pessoaModelo);
        return "A pessoa com o id " +id_pessoa+ " foi removida com sucesso";
    }
    private PessoaModelo getEntityById(Long id_pessoa)
    {
        return pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new EntidadeNaoEncontrada(id_pessoa));
    }
    @Transactional
    public String emprestimoLivro(Long id_pessoa, long id_livro)
    {
        if(pessoaRepositorio.existsById(id_pessoa) && livroRepositorio.existsById(id_livro))
        {
            LivroModelo livro = livroRepositorio.findById(id_livro).get();
            if(livro.getQuantidade()>0)
            {
                PessoaModelo pessoa = getEntityById(id_pessoa);
                pessoa.getLivros().add(livro);
                int quantidade = livro.getQuantidade();
                livro.setQuantidade(quantidade-1);
                pessoaRepositorio.save(pessoa);
                return "O emprestimo do livro " +id_livro+ " foi efetuado com sucesso";
            }
            else
            {
                return "O livro " +id_livro+ " não está disponivel no momento";
            }
        }
        else if (livroRepositorio.existsById(id_livro))
        {
            return "A pessoa com o id " +id_pessoa+ " não está cadastrada";
        }
        else if (pessoaRepositorio.existsById(id_pessoa))
        {
            return "O livro com o id " +id_livro+ " não está cadastrado";
        }
        else
        {
            return "O livro e a pessoa do id respectivo não estão cadastrados";
        }
    }
    @Transactional
    public String devolverLivro(Long id_pessoa, long id_livro)
    {
        if(pessoaRepositorio.existsById(id_pessoa) && livroRepositorio.existsById(id_livro))
        {
            PessoaModelo pessoa = getEntityById(id_pessoa);
            LivroModelo livro = livroRepositorio.findById(id_livro).get();
            pessoa.getLivros().remove(livro);
            int quantidade = livro.getQuantidade();
            livro.setQuantidade(quantidade+1);
            pessoaRepositorio.save(pessoa);
            return "A devolução do livro " +id_livro+ " foi efetuada com sucesso";
        }
        else if (livroRepositorio.existsById(id_livro))
        {
            return "A pessoa com o id " +id_pessoa+ " não está cadastrada";
        }
        else if (pessoaRepositorio.existsById(id_pessoa))
        {
            return "O livro com o id " +id_livro+ " não está cadastrado";
        }
        else
        {
            return "O livro e a pessoa do id respectivo não estão cadastrados";
        }
    }
}
