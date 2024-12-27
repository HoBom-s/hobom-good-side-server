package hobom.backend.application.category.handler

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.FindCategoryAll
import hobom.backend.common.handler.QueryHandler
import hobom.backend.common.query.ListResponse
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Component

@Component
class FindCategoryAllHandler(
    private val categoryRepository: CategoryRepository,
) : QueryHandler<FindCategoryAll, ListResponse<CategoryQueryResponse>> {
    override fun handle(query: FindCategoryAll): ListResponse<CategoryQueryResponse> {
        return ListResponse(categoryRepository.findAll().map { CategoryQueryResponse.from(it) })
    }
}
