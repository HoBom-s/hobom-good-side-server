package hobom.backend.application.category.dto.command

import hobom.backend.common.command.Command

data class DeleteCategory(val id: Long) : Command
