package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.service.LivroServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroControle
{
    @Autowired
    private LivroServico livroServico;
    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDto>> getAllLivros()
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.getAllLivros());
    }
    @GetMapping(value = "/{id_livro}")
    public ResponseEntity<LivroResponseDto> getLivroById(@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.getLivroById(id_livro));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDto> createLivro(@Valid @RequestBody LivroRequestDto livroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.createLivro(livroRequestDto));
    }
    @PutMapping(value = "/update/{id_livro}")
    public ResponseEntity<LivroResponseDto> updateLivro(@PathVariable Long id_livro ,@Valid @RequestBody LivroRequestDto livroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.updateLivro(id_livro,livroRequestDto));
    }
    @DeleteMapping(value = "/delete/{id_livro}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.deleteLivro(id_livro));
    }
}
