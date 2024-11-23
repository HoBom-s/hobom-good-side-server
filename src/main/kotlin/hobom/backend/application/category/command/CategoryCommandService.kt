package hobom.backend.application.category.command

import hobom.backend.application.category.dto.CategoryChangeIndexCommand
import hobom.backend.application.category.dto.CategoryCommand
import hobom.backend.application.category.dto.CategoryUpdateCommand

interface CategoryCommandService {
    fun createCategory(command: CategoryCommand)

    fun updateCategory(id: Long, command: CategoryUpdateCommand)

    fun deleteCategory(id: Long)

    fun changeSortIndex(id: Long, command: CategoryChangeIndexCommand)
}