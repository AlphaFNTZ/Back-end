package com.example.emprestimo_de_livros.infra.segurança;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.emprestimo_de_livros.entities.PessoaModelo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServico
{
    @Value("${api.seguranca.token.secreto}")
    private String secreto;

    public String gerarToken(PessoaModelo pessoaModelo)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secreto); //hash
            String token = JWT.create()
                    .withIssuer("emprestimo_de_livros") // dizendo qual é a aplicação caso tenha mais
                    .withSubject(pessoaModelo.getEmail()) // dizendo quem é o objeto que vamos usar como parametro
                    .withExpiresAt(this.tempoExpiracao()) // tempo que o token vai durar
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException exception)
        {
            throw new RuntimeException("Erro ao gerar token"); // pegando um possivel erro
        }
    }
    public String validacaoToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secreto); //hash
            return JWT.require(algorithm).withIssuer("emprestimo_de_livros")
                    .build().verify(token)
                    .getSubject();
        }
        catch (JWTVerificationException exception) {
            return null;
        }
    }
    private Instant tempoExpiracao()
    {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // calculo do tempo de duração do "token"
    }
}
