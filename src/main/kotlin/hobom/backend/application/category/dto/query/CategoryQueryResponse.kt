package hobom.backend.application.category.dto.query

import hobom.backend.common.query.Response
import hobom.backend.domain.model.category.Category
import java.time.LocalDateTime

data class CategoryQueryResponse(
    val id: Long,
    val title: String,
    val path: String,
    val sortIndex: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) : Response {
    companion object {
        fun from(category: Category): CategoryQueryResponse {
            return CategoryQueryResponse(
                id = category.id,
                title = category.title,
                path = category.path,
                sortIndex = category.sortIndex,
                createdAt = category.createdAt,
                updatedAt = category.updatedAt,
            )
        }
    }
}
