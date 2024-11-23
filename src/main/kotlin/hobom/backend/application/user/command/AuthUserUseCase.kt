package hobom.backend.application.user.command

import hobom.backend.application.user.dto.AuthResponse
import hobom.backend.application.user.dto.UserCommand
import hobom.backend.application.user.dto.UserLoginCommand

interface AuthUserUseCase {
    fun createUser(command: UserCommand)

    fun loginUser(command: UserLoginCommand): AuthResponse
}
