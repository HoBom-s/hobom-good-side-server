package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.FindCategoryById
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.QueryBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("findCategoryDetailUseCase")
class FindCategoryDetailUseCase(
    private val queryBus: QueryBus,
) : UseCase<Request.FindCategoryDetail, Response.FindCategoryDetail> {
    override fun execute(request: Request.FindCategoryDetail): Response.FindCategoryDetail {
        val query = FindCategoryById(request.id)
        val response = queryBus.execute<FindCategoryById, CategoryQueryResponse>(query)

        return Response.FindCategoryDetail(
            id = response.id,
            title = response.title,
            path = response.path,
            sortIndex = response.sortIndex,
            createdAt = response.createdAt,
            updatedAt = response.updatedAt,
        )
    }
}
