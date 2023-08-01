package com.study.base.boot.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenApi(){
        final var info = new Info()
                .title("Study API")
                .description("Study Rest API")
                .version("v1.0");

        return new OpenAPI().info(info);
    }
}
