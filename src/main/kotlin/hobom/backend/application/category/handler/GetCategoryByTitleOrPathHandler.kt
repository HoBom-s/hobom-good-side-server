package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.GetCategoryByTitleOrPath
import hobom.backend.common.handler.QueryHandler
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Component

@Component
class GetCategoryByTitleOrPathHandler(
    private val categoryRepository: CategoryRepository,
) : QueryHandler<GetCategoryByTitleOrPath, CategoryQueryResponse?> {
    override fun handle(query: GetCategoryByTitleOrPath): CategoryQueryResponse? {
        val response = categoryRepository.findByTitleOrPath(query.title, query.path)
            ?: return null

        return CategoryQueryResponse.from(response)
    }
}
