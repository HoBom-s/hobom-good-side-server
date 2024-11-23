package hobom.backend.application.user.dto

import hobom.backend.domain.model.user.User
import hobom.backend.domain.model.user.UserRole
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val seasonId: Long,
    val nickname: String,
    val name: String,
    val role: UserRole,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                seasonId = user.seasonId,
                nickname = user.nickname,
                name = user.name,
                role = user.role,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
            )
        }
    }
}
