package com.example.emprestimo_de_livros.infra.segurança;

import com.example.emprestimo_de_livros.entities.PessoaModelo;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class FiltroSeguranca extends OncePerRequestFilter
{
    @Autowired
    TokenServico tokenServico;
    @Autowired
    PessoaRepositorio pessoaRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request); // pega o token do usuario
        var login = tokenServico.validacaoToken(token); // verifica e valida o token

        if (login != null) // caso esteja logado
        {
            PessoaModelo pessoaModelo = pessoaRepositorio.findByEmail(login).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            var autoridades = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var autenticacao = new UsernamePasswordAuthenticationToken(pessoaModelo, null, autoridades);
            SecurityContextHolder.getContext().setAuthentication(autenticacao); // adiciona o usuario no contexto
        }
        filterChain.doFilter(request, response); // caso nao esteja logado, nao vai adicionar nada
    }
    private String recuperarToken(HttpServletRequest request)
    {
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
