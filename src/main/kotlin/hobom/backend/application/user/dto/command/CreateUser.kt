package hobom.backend.application.user.dto.command

import hobom.backend.common.command.Command
import hobom.backend.domain.model.user.UserRole

data class CreateUser(
    val seasonId: Long,
    val nickname: String,
    val password: String,
    val name: String,
    val role: UserRole,
) : Command
