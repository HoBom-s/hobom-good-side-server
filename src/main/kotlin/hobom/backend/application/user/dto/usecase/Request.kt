package hobom.backend.application.user.dto.usecase

import hobom.backend.domain.model.user.UserRole

sealed class Request {
    data class CreateUserRequest(
        val seasonId: Long,
        val nickname: String,
        val password: String,
        val name: String,
        val role: UserRole,
    ) : Request()
    data class GetUserByNickname(val nickname: String) : Request()
    data class LoginUser(
        val nickname: String,
        val password: String,
    )
}
