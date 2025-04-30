package br.com.mybooks.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API MyBooks")
                        .version("v1")
                        .description("Documentação da API MyBooks"));
    }

    @Bean
    public OpenApiCustomizer globalHeaderCustomiser() {
        return openApi -> openApi.getPaths().forEach((path, pathItem) -> {
            if (!path.startsWith("/auth")) {
                pathItem.readOperations().forEach(operation -> operation.addParametersItem(new HeaderParameter()
                        .name("Authorization")
                        .description("Token JWT")
                        .required(true)
                        .example("Bearer eyJhbGciOiJIUzI1NiIsInR...")
                        .schema(new io.swagger.v3.oas.models.media.StringSchema())));
            }
        });
    }

}
