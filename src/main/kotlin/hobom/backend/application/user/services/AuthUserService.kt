package hobom.backend.application.user.services

import hobom.backend.application.user.dto.usecase.AuthResponse
import hobom.backend.application.user.dto.usecase.Request
import hobom.backend.application.user.dto.usecase.Response
import hobom.backend.application.user.exception.UserNotFoundException
import hobom.backend.common.auth.JwtTokenProvider
import hobom.backend.common.usecase.UseCase
import hobom.backend.domain.model.user.User
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthUserService(
    @Qualifier("createUserUseCase")
    private val createUserUseCase: UseCase<Request.CreateUserRequest, Response.Empty>,
    @Qualifier("getUserByNicknameUseCase")
    private val getUserByNicknameUseCase: UseCase<Request.GetUserByNickname, Response.GetUserByNicknameResponse>,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    fun createUser(request: Request.CreateUserRequest) {
        verifyUserCreateExists(request.nickname)
        createUserUseCase.execute(request)
    }

    fun loginUser(request: Request.LoginUser): AuthResponse {
        val user = findUserIfExist(request.nickname)

        validateUserAndMatchPassword(rawPassword = request.password, encodedPassword = user.password)

        return generateToken(user.toEntity())
    }

    private fun verifyUserCreateExists(nickname: String) {
        val foundUser = getUserByNicknameUseCase.execute(Request.GetUserByNickname(nickname))

        check(foundUser == null) {
            throw IllegalArgumentException("이미 해당 닉네임의 사용자가 존재해요. $nickname")
        }
    }

    private fun findUserIfExist(nickname: String): Response.GetUserByNicknameResponse {
        val foundUser = getUserByNicknameUseCase.execute(Request.GetUserByNickname(nickname))

        return requireNotNull(foundUser) { throw UserNotFoundException("해당 유저가 존재하지 않아요.") }
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

    private fun Response.GetUserByNicknameResponse.toEntity(): User {
        return User(
            seasonId = this.seasonId,
            nickname = this.nickname,
            password = this.password,
            name = this.name,
            role = this.role,
        )
    }
}
