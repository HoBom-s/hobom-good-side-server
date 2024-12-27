package hobom.backend.application.category.dto.command

import hobom.backend.common.command.Command

data class UpdateCategory(
    val id: Long,
    val title: String?,
    val path: String?,
    val sortIndex: Int?,
) : Command
