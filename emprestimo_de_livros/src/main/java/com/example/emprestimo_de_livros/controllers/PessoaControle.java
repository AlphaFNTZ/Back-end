package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.LoginRequestDto;
import com.example.emprestimo_de_livros.dtos.request.RegistroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LoginResponseDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.entities.PessoaModelo;
import com.example.emprestimo_de_livros.infra.segurança.TokenServico;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import com.example.emprestimo_de_livros.service.PessoaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaControle {
    @Autowired
    private PessoaServico pessoaServico;
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenServico tokenServico;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoa() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getAllPessoa());
    }

    @GetMapping(value = "/{id_pessoa}")
    public ResponseEntity<PessoaResponseDto> getPessoaById(@PathVariable Long id_pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getPessoaById(id_pessoa));
    }

    @PutMapping(value = "/update/{id_pessoa}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable Long id_pessoa, @Valid @RequestBody RegistroRequestDto registroRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.updatePessoa(id_pessoa, registroRequestDto));
    }

    @DeleteMapping(value = "/delete/{id_pessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id_pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.deletePessoa(id_pessoa));
    }

    // Interação com a tabela emprestimo
    @PostMapping(value = "/emprestimo/{id_pessoa}/{id_livro}")
    public ResponseEntity<String> emprestimo(@PathVariable Long id_pessoa, @PathVariable Long id_livro) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.emprestimoLivro(id_pessoa, id_livro));
    }

    @DeleteMapping(value = "/devolucao/{id_pessoa}/{id_livro}")
    public ResponseEntity<String> devolver(@PathVariable Long id_pessoa, @PathVariable Long id_livro) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.devolverLivro(id_pessoa, id_livro));
    }

    // Registro e Login
    @PostMapping("/registro")
    public ResponseEntity<PessoaResponseDto> registro(@Valid @RequestBody RegistroRequestDto registroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.resgistro(registroRequestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.login(loginRequestDto));
    }
    /*@PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        PessoaModelo pessoaModelo = this.pessoaRepositorio.findByEmail(loginRequestDto.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (passwordEncoder.matches(loginRequestDto.senha(), pessoaModelo.getSenha())) {
            String token = this.tokenServico.gerarToken(pessoaModelo);
            return ResponseEntity.ok(new LoginResponseDto(pessoaModelo.getNome_pessoa(), token));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }*/
    /*@PostMapping("/registro")
    public ResponseEntity registro (@RequestBody RegistroRequestDto registroRequestDto)
    {
        Optional<PessoaModelo> pessoaModelo = this.pessoaRepositorio.findByEmail(registroRequestDto.email());
        if(pessoaModelo.isEmpty())
        {
            PessoaModelo newPessoaModelo = new PessoaModelo();
            newPessoaModelo.setNome_pessoa(registroRequestDto.nome_pessoa());
            newPessoaModelo.setCep(registroRequestDto.cep());
            newPessoaModelo.setEmail(registroRequestDto.email());
            newPessoaModelo.setSenha(passwordEncoder.encode(registroRequestDto.senha()));
            this.pessoaRepositorio.save(newPessoaModelo);
            String token = this.tokenServico.gerarToken(newPessoaModelo);
            return ResponseEntity.ok(new LoginResponseDto(newPessoaModelo.getNome_pessoa(), token));
        } else
        {
            return ResponseEntity.badRequest().build();
        }
    }*/
}
