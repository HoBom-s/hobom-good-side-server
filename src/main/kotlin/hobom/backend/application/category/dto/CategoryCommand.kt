package hobom.backend.application.category.dto

data class CategoryCommand(
    val title: String,
    val path: String,
    val sortIndex: Int,
)

data class CategoryUpdateCommand(
    val title: String? = null,
    val path: String? = null,
    val sortIndex: Int? = null,
)

data class CategoryChangeIndexCommand(
    val sortIndex: Int,
)