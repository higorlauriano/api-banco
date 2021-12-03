package com.higor.banco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.higor.banco.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(new ApiKey("JWT", HttpHeaders.AUTHORIZATION, "header")))
                .securityContexts(Arrays.asList(securityContext()))
                .apiInfo(generateApiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authScope = new AuthorizationScope("admin", "accessEverything");

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authScope;

        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }


    private ApiInfo generateApiInfo() {
        final StringBuilder sbDesc = new StringBuilder();
        sbDesc.append("API Rest para uma instituição financeira fictítica\n\n");
        sbDesc.append("Rota de autenticação: */api/v1/token*\n\n");
        sbDesc.append("Informar no body tipo Form:\n\n");
        sbDesc.append("usuario: usuarioTeste\n");
        sbDesc.append("senha: senhaTeste:\n\n");
        sbDesc.append("Header Authorization: Basic bDdlNjk5YjQ2ZDliYWI0N2NiYjkwNDllNWQyMDM3ZTNlYzo1NTRkYjExY2Y3MzI0ZGM5ODJjMzY0YjgwOGE3ODUxYQ==\n");

        return new ApiInfoBuilder()
                .title("API Simples de Banco")
                .description(sbDesc.toString())
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Higor Lauriano", "https://github.com/higorlauriano", "higor.lauriano@gmail.com"))
                .build();
    }

}
