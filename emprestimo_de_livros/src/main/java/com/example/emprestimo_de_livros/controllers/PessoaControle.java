package com.example.emprestimo_de_livros.controllers;

import com.example.emprestimo_de_livros.dtos.request.LoginRequestDto;
import com.example.emprestimo_de_livros.dtos.request.RegistroRequestDto;
import com.example.emprestimo_de_livros.dtos.response.LoginResponseDto;
import com.example.emprestimo_de_livros.dtos.response.PessoaResponseDto;
import com.example.emprestimo_de_livros.infra.segurança.TokenServico;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import com.example.emprestimo_de_livros.service.PessoaServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Tag(name = "Pessoas", description = "Endpoints relacionados ao banco de registro de pessoas")
    @Operation(summary = "Listar todas as pessoas",
            description = "Gera uma lista de todas as pessoas que estão cadastradas no banco de dados",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<PessoaResponseDto>> getAllPessoa()
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getAllPessoa());
    }

    @Operation(summary = "Buscar os dados de uma pessoas",
            description = "Retorna os dados de uma pessoa atraves de uma busca por id",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value = "/{id_pessoa}", produces = "application/json")
    public ResponseEntity<PessoaResponseDto> getPessoaById(@PathVariable Long id_pessoa)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.getPessoaById(id_pessoa));
    }

    @Operation(summary = "Alterar os dados de uma pessoa",
            description = "Modifica os dados de uma pessoa atraves de uma busca por id",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PutMapping(value = "/update/{id_pessoa}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PessoaResponseDto> updatePessoa(@PathVariable Long id_pessoa, @Valid @RequestBody RegistroRequestDto registroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.updatePessoa(id_pessoa, registroRequestDto));
    }

    @Operation(summary = "Apagar os dados de uma pessoas",
            description = "Apaga todos os dados de uma pessoa atraves de uma busca por id",
            tags = {"Pessoas"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/delete/{id_pessoa}", produces = "application/json")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id_pessoa)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.deletePessoa(id_pessoa));
    }

    // Interação com a tabela emprestimo

    @Tag(name = "Emprestimo e devolução", description = "Endpoints relacionados ao emprestimo e devolução de livros por pessoa")
    @Operation(summary = "Realiza um emprestimo",
            description = "Registra um emprestimo realizado de um livro para uma pessoa",
            tags = {"Emprestimo e devolução"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/emprestimo/{id_pessoa}/{id_livro}",produces = "application/json")
    public ResponseEntity<String> emprestimo(@PathVariable Long id_pessoa, @PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.emprestimoLivro(id_pessoa, id_livro));
    }

    @Operation(summary = "Realiza uma devolução",
            description = "Apagar os dados no banco de dados de um emprestimo realizado de um livro para uma pessoa",
            tags = {"Emprestimo e devolução"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @DeleteMapping(value = "/devolucao/{id_pessoa}/{id_livro}", produces = "application/json")
    public ResponseEntity<String> devolver(@PathVariable Long id_pessoa, @PathVariable Long id_livro)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.devolverLivro(id_pessoa, id_livro));
    }

    // Registro e Login

    @Tag(name = "Registro e login", description = "Endpoints relacionados ao registro e login de usuarios")
    @Operation(summary = "Realiza o registro de uma pessoa",
            description = "Registra um novo usuario como uma pessoa dentro do banco de dados",
            tags = {"Registro e login"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/registro", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PessoaResponseDto> registro(@Valid @RequestBody RegistroRequestDto registroRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.resgistro(registroRequestDto));
    }

    @Operation(summary = "Realiza o login de uma pessoa",
            description = "Loga o usuario dentro da aplicação e gera uma chave de acesso (token)",
            tags = {"Registro e login"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDto.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaServico.login(loginRequestDto));
    }
}
