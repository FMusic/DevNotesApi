package org.dn

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun configure(): OpenAPI{
        return OpenAPI().components(Components())
                .info(Info().title("Dev Notes API")
                        .description("REST API for accessing to Dev Notes"))
    }
}