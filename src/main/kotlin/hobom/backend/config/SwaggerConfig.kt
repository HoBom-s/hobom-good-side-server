package hobom.backend.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(Components())
        .info(configurationInfo())

    private fun configurationInfo(): Info = Info()
        .title("HoBom Tech Blog Kotlin + Spring Boot")
        .description("API DOCS")
        .version("1.0.0")
}
