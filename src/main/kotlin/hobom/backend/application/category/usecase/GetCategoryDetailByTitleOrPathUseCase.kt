package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.GetCategoryByTitleOrPath
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.QueryBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("getCategoryDetailByTitleOrPathUseCase")
class GetCategoryDetailByTitleOrPathUseCase(
    private val queryBus: QueryBus,
) : UseCase<Request.GetCategoryDetailByTitleOrPath, Response.GetCategoryDetail?> {
    override fun execute(request: Request.GetCategoryDetailByTitleOrPath): Response.GetCategoryDetail? {
        val query = GetCategoryByTitleOrPath(request.title, request.path)
        val response = queryBus.execute<GetCategoryByTitleOrPath, CategoryQueryResponse?>(query)
            ?: return null

        return Response.GetCategoryDetail(
            id = response.id,
            title = response.title,
            path = response.path,
            sortIndex = response.sortIndex,
            createdAt = response.createdAt,
            updatedAt = response.updatedAt,
        )
    }
}
