package com.example.udpm14sellcomputerpartsbackend.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Application to sell compputer")
                                .description("List of APIs")
                                .version("v1.0")
                                .contact(
                                        new Contact()
                                                .name("DuyDucComputer")
                                                .email("ducndph16372@fpt.edu.vn")
                                )
                );
    }


}
