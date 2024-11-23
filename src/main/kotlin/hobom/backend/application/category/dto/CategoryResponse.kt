package hobom.backend.application.category.dto

import hobom.backend.domain.model.category.Category
import java.time.LocalDateTime

data class CategoryResponse(
    val id: Long,
    val title: String,
    val path: String,
    val sortIndex: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(category: Category): CategoryResponse {
            return CategoryResponse(
                id = category.id,
                title = category.title,
                path = category.path,
                sortIndex = category.sortIndex,
                createdAt = category.createdAt,
                updatedAt = category.updatedAt
            )
        }
    }
}

fun CategoryResponse.toEntity(): Category {
    return Category(
        id = this.id,
        title = this.title,
        path = this.path,
        sortIndex = this.sortIndex,
    )
}