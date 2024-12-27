package hobom.backend.application.category.dto.usecase

sealed class Request {
    data class FindCategoryDetail(val id: Long) : Request()
    data class GetCategoryDetailByTitleOrPath(val title: String, val path: String) : Request()
    data class CreateCategory(
        val title: String,
        val path: String,
        val sortIndex: Int,
    )
    data class UpdateCategory(
        val id: Long,
        val title: String,
        val path: String,
        val sortIndex: Int,
    )
    data class DeleteCategory(val id: Long) : Request()
    class FindCategories : Request()
}
