package hobom.backend.application.category.command

import hobom.backend.application.category.dto.CategoryChangeIndexCommand
import hobom.backend.application.category.dto.CategoryCommand
import hobom.backend.application.category.dto.CategoryUpdateCommand
import hobom.backend.application.category.dto.toEntity
import hobom.backend.application.category.query.CategoryQueryService
import hobom.backend.domain.model.category.Category
import hobom.backend.domain.repository.category.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultCategoryCommandService(
    private val categoryQueryService: CategoryQueryService,
    private val categoryRepository: CategoryRepository,
) : CategoryCommandService {
    @Transactional
    override fun createCategory(command: CategoryCommand) {
        checkCategoryExistence(command = command)

        categoryRepository.save(command.toEntity())
    }

    @Transactional
    override fun updateCategory(id: Long, command: CategoryUpdateCommand) {
        val foundCategory = findCategoryById(id = id)

        foundCategory.update(
            title = command.title,
            path = command.path,
            sortIndex = command.sortIndex,
        )
    }

    @Transactional
    override fun deleteCategory(id: Long) {
        val foundCategory = findCategoryById(id = id)

        categoryRepository.deleteById(foundCategory.id)
    }

    @Transactional
    override fun changeSortIndex(id: Long, command: CategoryChangeIndexCommand) {
        val foundCategory = findCategoryById(id = id)

        foundCategory.changeIndex(
            sortIndex = command.sortIndex,
        )
    }

    private fun checkCategoryExistence(command: CategoryCommand) {
        val alreadyExistCategory = categoryQueryService.searchCategoryByTitleOrPath(
            title = command.title,
            path = command.path,
        )

        if (alreadyExistCategory != null) {
            throw IllegalArgumentException("이미 존재하는 카테고리에요. title: ${command.title}, path: ${command.path}")
        }
    }

    private fun findCategoryById(id: Long): Category {
        return categoryQueryService.findCategoryById(id)
    }

    private fun CategoryCommand.toEntity(): Category {
        return Category(
            title = this.title,
            path = this.path,
            sortIndex = this.sortIndex,
        )
    }
}
