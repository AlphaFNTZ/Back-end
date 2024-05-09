package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.LivroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LivroResponseDto;
import com.example.emprestimo_de_livros.service.LivroServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@Tag(name = "Livros", description = "Endpoints relacionados ao banco de registro de livros")
public class LivroControle
{
    @Autowired
    private LivroServico livroServico;

    @Operation(summary = "Listar todas os livros",
            description = "Gera uma lista de todas os livros que estão cadastradas no banco de dados",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<LivroResponseDto>> getAllLivros()
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.getAllLivros());
    }

    @Operation(summary = "Busca os dados de um livro",
            description = "Retorna os dados de um livro atraves de uma busca por id",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{id_livro}", produces = "application/json")
    public ResponseEntity<LivroResponseDto> getLivroById(@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.getLivroById(id_livro));
    }

    @Operation(summary = "Cria dados de um livro",
            description = "Insere novos dados de um novo livro no banco de dados",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LivroResponseDto> createLivro(@Valid @RequestBody LivroRequestDto livroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.createLivro(livroRequestDto));
    }

    @Operation(summary = "Altera os dados de um livro",
            description = "Modifica os dados de um livro atraves de uma busca por id",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LivroResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{id_livro}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LivroResponseDto> updateLivro(@PathVariable Long id_livro ,@Valid @RequestBody LivroRequestDto livroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.updateLivro(id_livro,livroRequestDto));
    }

    @Operation(summary = "Apaga os dados de um livro",
            description = "Apaga todos os dados de um um livro atraves de uma busca por id",
            tags = {"Livros"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{id_livro}", produces = "application/json")
    public ResponseEntity<String> deleteLivro(@PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(livroServico.deleteLivro(id_livro));
    }
}
