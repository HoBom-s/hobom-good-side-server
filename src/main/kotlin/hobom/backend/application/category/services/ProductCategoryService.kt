package hobom.backend.application.category.services

import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.application.category.exception.CategoryNotFoundException
import hobom.backend.common.query.ListResponse
import hobom.backend.common.usecase.UseCase
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ProductCategoryService(
    @Qualifier("findCategoryDetailUseCase")
    private val findCategoryDetailUseCase: UseCase<Request.FindCategoryDetail, Response.FindCategoryDetail>,
    @Qualifier("findCategoriesUseCase")
    private val findCategoriesUseCase: UseCase<Request.FindCategories, ListResponse<Response.FindCategoryDetail>>,
    @Qualifier("getCategoryDetailByTitleOrPathUseCase")
    private val getCategoryDetailByTitleOrPathUseCase: UseCase<Request.GetCategoryDetailByTitleOrPath, Response.GetCategoryDetail?>,
    @Qualifier("createCategoryUseCase")
    private val createCategoryUseCase: UseCase<Request.CreateCategory, Response.Empty>,
    @Qualifier("deleteCategoryUseCase")
    private val deleteCategoryUseCase: UseCase<Request.DeleteCategory, Response.Empty>,
) {
    fun findCategoryDetail(id: Long): Response.FindCategoryDetail {
        return findCategoryById(id)
    }

    fun findCategoryAll(): ListResponse<Response.FindCategoryDetail> {
        return findCategoriesUseCase.execute(Request.FindCategories())
            ?: throw CategoryNotFoundException("카테고리를 찾을 수 없어요.")
    }

    fun createCategoryUseCase(request: Request.CreateCategory) {
        val alreadyExist = getCategoryDetailByTitleOrPathUseCase.execute(
            Request.GetCategoryDetailByTitleOrPath(request.title, request.path),
        )

        check(alreadyExist == null) { throw IllegalArgumentException("이미 존재하는 카테고리예요.") }
        createCategoryUseCase.execute(request)
    }

    fun deleteCategoryUseCase(id: Long) {
        val category = findCategoryById(id)

        deleteCategoryUseCase.execute(Request.DeleteCategory(category.id))
    }

    private fun findCategoryById(id: Long): Response.FindCategoryDetail {
        val category = findCategoryDetailUseCase.execute(Request.FindCategoryDetail(id))

        return requireNotNull(category) { throw CategoryNotFoundException("카테고리를 찾을 수 없어요.") }
    }
}
