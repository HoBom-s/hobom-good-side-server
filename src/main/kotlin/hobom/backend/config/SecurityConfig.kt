package hobom.backend.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Autowired

    @Bean
    fun filterChain(http: HttpSecurity) = http
        .sessionManagement { config -> config.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        }
        .httpBasic { config -> config.disable() }
        .cors { config -> config.disable() }
        .csrf { config -> config.disable() }
        .build()

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
