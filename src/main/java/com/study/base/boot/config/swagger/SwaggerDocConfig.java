package com.study.base.boot.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.base.boot.config.constants.HeaderConstants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition(
    security = {
        @SecurityRequirement(name = HeaderConstants.X_ACCESS_TOKEN),
        @SecurityRequirement(name = HeaderConstants.X_REFRESH_TOKEN),
    }
)
@SecuritySchemes({
    @SecurityScheme(name = HeaderConstants.X_ACCESS_TOKEN,
        type = SecuritySchemeType.APIKEY,
        description = "Api token",
        in = SecuritySchemeIn.HEADER,
        paramName = HeaderConstants.X_ACCESS_TOKEN),
    @SecurityScheme(name = HeaderConstants.X_REFRESH_TOKEN,
        type = SecuritySchemeType.APIKEY,
        description = "Api refresh token",
        in = SecuritySchemeIn.HEADER,
        paramName = HeaderConstants.X_REFRESH_TOKEN),
})
@Configuration
public class SwaggerDocConfig {
}
