package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.models.LivroModel;
import com.example.emprestimo_de_livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroControle {
    @Autowired
    private LivroService livroService;
    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDto>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAllLivros());
    }
    @GetMapping(value = "/{id_livro}")
    public ResponseEntity<LivroResponseDto> getLivroById(@PathVariable Long id_livro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivroById(id_livro));
    }
    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDto> createLivro(@RequestBody LivroRequestDto livroRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.createLivro(livroRequestDto));
    }
    @PutMapping(value = "/update/{id_livro}")
    public ResponseEntity<LivroResponseDto> updateLivro(@PathVariable Long id_livro ,@RequestBody LivroRequestDto livroRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(id_livro,livroRequestDto));
    }
    @DeleteMapping(value = "/delete/{id_livro}")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id_livro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(id_livro));
    }
}
