package com.example.emprestimo_de_livros.infra.configuracao;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API-Rest | Emprestimo de livros | PS. Emakers JR.") //titulo da documentacao
                        .version("v1") //versao da documentacao
                        .description("Uma aplicação REST para o controle eficiente dos empréstimos de livros em bibliotecas. Essa ferramenta simplifica o registro e monitoramento dos livros emprestados, proporcionando uma visão organizada do movimento de informações essenciais para o funcionamento da biblioteca.") //descricao da documentacao
                )
                .addServersItem(new Server().url("http://localhost:8080").description("Servidor local"))
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write")));
    }
}
