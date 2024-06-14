package com.example.emprestimo_de_livros.service;

import com.example.emprestimo_de_livros.dtos.request.LoginRequestDto;
import com.example.emprestimo_de_livros.dtos.request.RegistroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LoginResponseDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.exceptions.gerais.EntidadeNaoEncontrada;
import com.example.emprestimo_de_livros.exceptions.emprestimos.EmprestimoNaoExistente;
import com.example.emprestimo_de_livros.entities.LivroModelo;
import com.example.emprestimo_de_livros.entities.PessoaModelo;
import com.example.emprestimo_de_livros.exceptions.senha.SenhaErrada;
import com.example.emprestimo_de_livros.infra.segurança.TokenServico;
import com.example.emprestimo_de_livros.repositories.LivroRepositorio;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaServico
{
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private LivroRepositorio livroRepositorio;
    @Autowired
    private TokenServico tokenServico;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<PessoaResponseDto> getAllPessoa ()
    {
        List<PessoaModelo> pessoas = pessoaRepositorio.findAll();
        return pessoas.stream().map(PessoaResponseDto::new).collect(Collectors.toList());
    }
    public PessoaResponseDto getPessoaById (Long id_pessoa)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        return new PessoaResponseDto(pessoaModelo);
    }
    public PessoaResponseDto updatePessoa (Long id_pessoa, @Valid RegistroRequestDto registroRequestDto)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        pessoaModelo.setNome_pessoa(registroRequestDto.nome_pessoa());
        pessoaModelo.setCep(registroRequestDto.cep());
        pessoaModelo.setEmail(registroRequestDto.email());
        pessoaModelo.setSenha(passwordEncoder.encode(registroRequestDto.senha()));
        pessoaRepositorio.save(pessoaModelo);
        return new PessoaResponseDto(pessoaModelo);
    }
    @Transactional
    public String deletePessoa (Long id_pessoa)
    {
        PessoaModelo pessoaModelo = getEntityById(id_pessoa);
        if (pessoaModelo.getLivros().isEmpty()) {
            pessoaRepositorio.delete(pessoaModelo);
            return "A pessoa com o id " + id_pessoa + " foi removida com sucesso";
        } else throw new EmprestimoNaoExistente(id_pessoa, "pessoa");
    }
    private PessoaModelo getEntityById(Long id_pessoa)
    {
        return pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new EntidadeNaoEncontrada(id_pessoa));
    }

    // Emprestimo e devolucao

    @Transactional
    public String emprestimoLivro(Long id_pessoa, long id_livro)
    {
        LivroModelo livro = livroRepositorio.findById(id_livro).orElseThrow(() -> new EntidadeNaoEncontrada(id_livro, "livro"));
        PessoaModelo pessoa = pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new EntidadeNaoEncontrada(id_pessoa, "pessoa"));
        int quantidade = livro.getQuantidade();
        if(livro.getQuantidade()>0) {
            if (pessoa.getLivros().contains(livro)) throw new EmprestimoNaoExistente(id_livro);
            pessoa.getLivros().add(livro);
            livro.setQuantidade(quantidade - 1);
            pessoaRepositorio.save(pessoa);
            return "O emprestimo do livro " + id_livro + " foi efetuado com sucesso";
        } else throw new EmprestimoNaoExistente(id_livro, quantidade);
    }
    @Transactional
    public String devolverLivro (Long id_pessoa, long id_livro)
    {
        LivroModelo livro = livroRepositorio.findById(id_livro).orElseThrow(() -> new EntidadeNaoEncontrada(id_livro, "livro"));
        PessoaModelo pessoa = pessoaRepositorio.findById(id_pessoa).orElseThrow(()-> new EntidadeNaoEncontrada(id_pessoa, "pessoa"));
        if (pessoa.getLivros().contains(livro) && livro.getPessoas().contains(pessoa)) {
            pessoa.getLivros().remove(livro);
            int quantidade = livro.getQuantidade();
            livro.setQuantidade(quantidade + 1);
            pessoaRepositorio.save(pessoa);
            return "A devolução do livro " + id_livro + " foi efetuada com sucesso";
        } else throw new EmprestimoNaoExistente(id_livro, id_pessoa);
    }

    // Registro e login

    public PessoaResponseDto resgistro (RegistroRequestDto registroRequestDto)
    {
        Optional <PessoaModelo> pessoaExiste = pessoaRepositorio.findByEmail(registroRequestDto.email());
        if (pessoaExiste.isPresent()){
            throw new EmprestimoNaoExistente(registroRequestDto.email());
        } else {
            PessoaModelo pessoaModelo = new PessoaModelo();
            pessoaModelo.setEmail(registroRequestDto.email());
            pessoaModelo.setSenha(passwordEncoder.encode(registroRequestDto.senha()));
            pessoaModelo.setCep(registroRequestDto.cep());
            pessoaModelo.setNome_pessoa(registroRequestDto.nome_pessoa());
            this.pessoaRepositorio.save(pessoaModelo);
            return new PessoaResponseDto(pessoaModelo);
        }
    }
    public LoginResponseDto login (LoginRequestDto loginRequestDto)
    {
        PessoaModelo pessoaModelo = pessoaRepositorio.findByEmail(loginRequestDto.email()).orElseThrow(() -> new EntidadeNaoEncontrada(loginRequestDto.email()));
        String token = this.tokenServico.gerarToken(pessoaModelo);
        if (passwordEncoder.matches(loginRequestDto.senha(), pessoaModelo.getSenha())) {
            return new LoginResponseDto(pessoaModelo.getNome_pessoa(), token);
        } else {
            throw new SenhaErrada(loginRequestDto.senha());
        }
    }
}
