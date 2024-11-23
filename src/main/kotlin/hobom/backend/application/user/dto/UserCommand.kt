package hobom.backend.application.user.dto

import hobom.backend.domain.model.user.User
import hobom.backend.domain.model.user.UserRole

data class UserCommand(
    val seasonId: Long,
    val nickname: String,
    val password: String,
    val name: String,
    val role: UserRole,
)

data class UserLoginCommand(
    val nickname: String,
    val password: String,
)

fun UserCommand.toEntity(): User {
    return User(
        seasonId = this.seasonId,
        nickname = this.nickname,
        password = this.password,
        name = this.name,
        role = this.role,
    )
}
