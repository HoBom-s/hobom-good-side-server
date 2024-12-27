package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.query.CategoryQueryResponse
import hobom.backend.application.category.dto.query.FindCategoryAll
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.QueryBus
import hobom.backend.common.query.ListResponse
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("findCategoriesUseCase")
class FindCategoriesUseCase(
    private val queryBus: QueryBus,
) : UseCase<Request.FindCategories, ListResponse<Response.FindCategoryDetail>> {
    override fun execute(request: Request.FindCategories): ListResponse<Response.FindCategoryDetail> {
        val query = FindCategoryAll()
        val response = queryBus.execute<FindCategoryAll, ListResponse<CategoryQueryResponse>>(query)

        val items = response.items.map {
            Response.FindCategoryDetail(
                id = it.id,
                title = it.title,
                path = it.path,
                sortIndex = it.sortIndex,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt,
            )
        }

        return ListResponse(items)
    }
}
