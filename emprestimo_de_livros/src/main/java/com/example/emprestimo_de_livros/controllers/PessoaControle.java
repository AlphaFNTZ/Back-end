package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.PessoaRequestDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.service.PessoaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaControle
{
    @Autowired
    private PessoaServico pessoaServico;
    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoa()
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getAllPessoa());
    }
    @GetMapping(value = "/{id_pessoa}")
    public ResponseEntity<PessoaResponseDto> getPessoaById(@PathVariable Long id_pessoa)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getPessoaById(id_pessoa));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDto> createPessoa(@Valid @RequestBody PessoaRequestDto pessoaRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.createPessoa(pessoaRequestDto));
    }
    @PutMapping(value = "/update/{id_pessoa}")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable Long id_pessoa ,@Valid @RequestBody PessoaRequestDto pessoaRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.updatePessoa(id_pessoa,pessoaRequestDto));
    }
    @DeleteMapping(value = "/delete/{id_pessoa}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id_pessoa)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.deletePessoa(id_pessoa));
    }
    // Interação com a tabela emprestimo
    @PostMapping(value="/emprestimo/{id_pessoa}/{id_livro}")
    public ResponseEntity<String>emprestimo(@PathVariable Long id_pessoa,@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.emprestimoLivro(id_pessoa,id_livro));
    }
    @DeleteMapping(value = "/devolucao/{id_pessoa}/{id_livro}")
    public ResponseEntity<String>devolver(@PathVariable Long id_pessoa,@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.devolverLivro(id_pessoa,id_livro));
    }
}
