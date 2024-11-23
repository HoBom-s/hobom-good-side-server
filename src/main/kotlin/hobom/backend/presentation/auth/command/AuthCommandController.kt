package hobom.backend.presentation.auth.command

import hobom.backend.application.user.command.AuthUserUseCase
import hobom.backend.application.user.dto.AuthResponse
import hobom.backend.application.user.dto.UserCommand
import hobom.backend.application.user.dto.UserLoginCommand
import hobom.backend.presentation.ExternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$ExternalPrefix/auth")
@Tag(
    name = "Auth External",
    description = "External Auth controller",
)
class AuthCommandController(
    private val authUserUseCase: AuthUserUseCase,
) {
    @PostMapping("/users")
    @Operation(summary = "사용자 회원가입")
    fun createUser(
        @RequestBody command: UserCommand,
    ): ResponseEntity<Unit> {
        authUserUseCase.createUser(command = command)

        return ResponseEntity.noContent().build()
    }

    @PostMapping("/login")
    @Operation(summary = "사용자 로그인")
    fun loginUser(
        @RequestBody command: UserLoginCommand,
    ): ResponseEntity<AuthResponse> {
        val userResponse = authUserUseCase.loginUser(command = command)

        return ResponseEntity.ok(userResponse)
    }
}
