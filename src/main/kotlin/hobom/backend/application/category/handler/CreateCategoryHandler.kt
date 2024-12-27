package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.command.CreateCategory
import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.exception.CategoryCreationException
import hobom.backend.common.handler.CommandHandler
import hobom.backend.domain.model.category.Category
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateCategoryHandler(
    private val categoryRepository: CategoryRepository,
) : CommandHandler<CreateCategory, Empty> {
    @Transactional
    override fun handle(command: CreateCategory): Empty {
        try {
            categoryRepository.save(command.toEntity())
        } catch (e: Exception) {
            throw CategoryCreationException("카테고리를 생성하지 못했어요.", e)
        }

        return Empty()
    }

    private fun CreateCategory.toEntity(): Category {
        return Category(
            title = this.title,
            path = this.path,
            sortIndex = this.sortIndex,
        )
    }
}
