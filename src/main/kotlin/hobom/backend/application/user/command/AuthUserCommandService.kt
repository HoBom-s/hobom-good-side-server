package hobom.backend.application.user.command

import hobom.backend.application.user.dto.AuthResponse
import hobom.backend.application.user.dto.UserCommand
import hobom.backend.application.user.dto.UserLoginCommand
import hobom.backend.application.user.dto.toEntity
import hobom.backend.common.auth.JwtTokenProvider
import hobom.backend.domain.model.user.User
import hobom.backend.domain.repository.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.naming.AuthenticationException

@Service
class AuthUserCommandService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) : AuthUserUseCase {
    @Transactional
    override fun createUser(command: UserCommand) {
        verifyUserExists(nickname = command.nickname)
        saveUser(command.toEntity())
    }

    override fun loginUser(command: UserLoginCommand): AuthResponse {
        val user = findUserByNickname(nickname = command.nickname)

        validateUserAndMatchPassword(rawPassword = command.password, encodedPassword = user.password)

        return generateToken(user = user)
    }

    private fun verifyUserExists(nickname: String) {
        val foundUser = userRepository.findByNickname(nickname)

        if (foundUser != null) {
            throw IllegalArgumentException("이미 해당 닉네임의 사용자가 존재해요. $nickname")
        }
    }

    private fun findUserByNickname(nickname: String): User {
        return userRepository.findByNickname(nickname = nickname)
            ?: throw NotFoundException()
    }

    private fun validateUserAndMatchPassword(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw AuthenticationException("사용자 정보가 일치하지 않아요.")
        }
    }

    private fun generateToken(user: User): AuthResponse {
        val accessToken = jwtTokenProvider.generateAccessToken(user = user)
        val refreshToken = jwtTokenProvider.generateRefreshToken(user = user)

        return AuthResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }

    private fun saveUser(user: User) {
        userRepository.save(user)
    }
}
