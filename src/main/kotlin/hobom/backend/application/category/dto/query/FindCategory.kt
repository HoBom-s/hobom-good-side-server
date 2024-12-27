package hobom.backend.application.category.dto.query

import hobom.backend.common.query.Query

data class FindCategoryById(val id: Long) : Query
data class GetCategoryByTitleOrPath(val title: String, val path: String) : Query
class FindCategoryAll : Query
