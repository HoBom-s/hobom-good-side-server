package hobom.backend.application.category.dto.usecase

import java.time.LocalDateTime

sealed class Response {
    data class FindCategoryDetail(
        val id: Long,
        val title: String,
        val path: String,
        val sortIndex: Int,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) : Response(), hobom.backend.common.query.Response

    data class GetCategoryDetail(
        val id: Long,
        val title: String,
        val path: String,
        val sortIndex: Int,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    ) : Response(), hobom.backend.common.query.Response

    class Empty() : Response()
}
