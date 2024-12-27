package hobom.backend.application.user.dto.query

import hobom.backend.common.query.Response
import hobom.backend.domain.model.user.UserRole
import java.time.LocalDateTime

data class UserQueryResponse(
    val id: Long,
    val seasonId: Long,
    val nickname: String,
    val password: String,
    val name: String,
    val role: UserRole,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) : Response
