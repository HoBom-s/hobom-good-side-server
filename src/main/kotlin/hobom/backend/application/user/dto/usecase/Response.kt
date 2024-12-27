package hobom.backend.application.user.dto.usecase

import hobom.backend.domain.model.user.UserRole
import java.time.LocalDateTime

sealed class Response {
    data class GetUserByNicknameResponse(
        val id: Long,
        val seasonId: Long,
        val nickname: String,
        val name: String,
        val password: String,
        val role: UserRole,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) : Response()

    class Empty : Response()
}
