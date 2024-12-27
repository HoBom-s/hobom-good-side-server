package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.dto.command.UpdateCategory
import hobom.backend.application.category.exception.CategoryNotFoundException
import hobom.backend.common.handler.CommandHandler
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UpdateCategoryHandler(
    private val categoryRepository: CategoryRepository,
) : CommandHandler<UpdateCategory, Empty> {
    @Transactional
    override fun handle(command: UpdateCategory): Empty {
        val category = categoryRepository.findById(command.id)
            .orElseThrow { throw CategoryNotFoundException("카테고리가 존재하지 않아요.") }

        category.update(
            title = command.title,
            path = command.path,
            sortIndex = command.sortIndex,
        )

        return Empty()
    }
}
