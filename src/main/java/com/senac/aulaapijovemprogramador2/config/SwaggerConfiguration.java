package com.senac.aulaapijovemprogramador2.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi(){

        return new OpenAPI()

                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .info(new Info()
                .title("API Cursos")
                .version("1.0")
                .description("API responsável por apresentar uma plataforma de cursos online")
                .termsOfService("https://circle.so/br/platform/creators?utm_medium=cpc&utm_source=google&utm_term=plataforma%20de%20cursos%20ead&utm_campaign=SG_UseCases_BR&hsa_acc=2981749656&hsa_cam=20522051952&hsa_grp=166355752293&hsa_ad=734189998022&hsa_src=g&hsa_tgt=kwd-144452049167&hsa_kw=plataforma%20de%20cursos%20ead&hsa_mt=e&hsa_net=adwords&hsa_ver=3&gad_source=1&gad_campaignid=20522051952&gbraid=0AAAAAoO5ug8amoV2OsvSAOVzTbwq5Fuvx&gclid=CjwKCAjwq9rFBhAIEiwAGVAZP-BR4h9GO8RabxDIH8NEhXJbwrVSOKNeUawQOhugf0lMY5zUbDTrqxoCP78QAvD_BwE")
        );
    }
}
