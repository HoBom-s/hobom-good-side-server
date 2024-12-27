package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.FindCategoryById
import hobom.backend.common.handler.QueryHandler
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Component

@Component
class FindCategoryByIdHandler(
    private val categoryRepository: CategoryRepository,
) : QueryHandler<FindCategoryById, CategoryQueryResponse> {
    override fun handle(query: FindCategoryById): CategoryQueryResponse {
        return categoryRepository.findById(query.id)
            .orElseThrow { throw ChangeSetPersister.NotFoundException() }
            .let { CategoryQueryResponse.from(it) }
    }
}
