package com.example.emprestimo_de_livros.infra.segurança;

import com.example.emprestimo_de_livros.entities.PessoaModelo;
import com.example.emprestimo_de_livros.repositories.PessoaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DetalhesUsuario implements UserDetailsService
{
    @Autowired
    private PessoaRepositorio pessoaRepositorio;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PessoaModelo pessoaModelo = this.pessoaRepositorio.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new org.springframework.security.core.userdetails.User(pessoaModelo.getEmail(), pessoaModelo.getSenha(), new ArrayList<>());
    }
}
