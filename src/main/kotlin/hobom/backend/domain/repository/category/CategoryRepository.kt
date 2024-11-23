package hobom.backend.domain.repository.category

import hobom.backend.domain.model.category.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findByTitle(title: String): Category?

    fun findByTitleAndPath(title: String, path: String): Category?
}