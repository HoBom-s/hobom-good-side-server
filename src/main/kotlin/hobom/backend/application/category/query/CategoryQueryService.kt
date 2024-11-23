package hobom.backend.application.category.query

import hobom.backend.application.category.dto.CategoryResponse
import hobom.backend.domain.model.category.Category

interface CategoryQueryService {
    fun findAll(): List<CategoryResponse>

    fun findCategoryById(id: Long): Category

    fun searchCategoryById(id: Long): CategoryResponse

    fun searchCategoryByTitle(title: String): CategoryResponse?

    fun searchCategoryByTitleOrPath(title: String, path: String): CategoryResponse?
}
