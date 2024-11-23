package hobom.backend.application.category.query

import hobom.backend.application.category.dto.CategoryResponse
import hobom.backend.domain.model.category.Category

interface CategoryQueryService {
    fun findCategoryById(id: Long): Category

    fun searchCategoryById(id: Long): CategoryResponse

    fun searchCategoryByTitle(title: String): CategoryResponse?

    fun searchCategoryByTitleAndPath(title: String, path: String): CategoryResponse?
}