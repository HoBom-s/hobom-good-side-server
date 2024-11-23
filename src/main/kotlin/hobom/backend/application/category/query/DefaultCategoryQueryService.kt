package hobom.backend.application.category.query

import hobom.backend.application.category.dto.CategoryResponse
import hobom.backend.domain.model.category.Category
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class DefaultCategoryQueryService(
    private val categoryRepository: CategoryRepository,
) : CategoryQueryService {
    override fun searchCategoryById(id: Long): CategoryResponse {
        val categoryResponse = with(this) {
            val category = findCategoryById(id = id)

            CategoryResponse.from(category = category)
        }

        return categoryResponse
    }

    override fun searchCategoryByTitle(title: String): CategoryResponse? {
        val category = with(this) {
            findCategoryByTitle(title = title)
        }

        if (category == null) {
            return null
        }

        return CategoryResponse.from(category = category)
    }

    override fun searchCategoryByTitleOrPath(title: String, path: String): CategoryResponse? {
        val category = with(this) {
            findCategoryByPathOrTitle(title = title, path = path)
        }

        if (category == null) {
            return null
        }

        return CategoryResponse.from(category = category)
    }

    override fun findCategoryById(id: Long): Category {
        return categoryRepository.findById(id)
            .orElseThrow { throw NotFoundException() }
    }

    private fun findCategoryByTitle(title: String): Category? {
        return categoryRepository.findByTitle(title = title)
    }

    private fun findCategoryByPathOrTitle(title: String, path: String): Category? {
        return categoryRepository.findByTitleOrPath(title = title, path = path)
    }
}
