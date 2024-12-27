package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.command.DeleteCategory
import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.exception.CategoryDeleteException
import hobom.backend.common.handler.CommandHandler
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DeleteCategoryHandler(
    private val categoryRepository: CategoryRepository,
) : CommandHandler<DeleteCategory, Empty> {
    @Transactional
    override fun handle(command: DeleteCategory): Empty {
        try {
            categoryRepository.deleteById(command.id)
        } catch (e: Exception) {
            throw CategoryDeleteException("카테고리 삭제 도중 오류가 발생했어요.", e)
        }

        return Empty()
    }
}
