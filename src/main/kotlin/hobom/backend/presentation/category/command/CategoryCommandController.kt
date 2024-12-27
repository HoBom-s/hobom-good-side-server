package hobom.backend.presentation.category.command

import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.services.ProductCategoryService
import hobom.backend.presentation.InternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$InternalPrefix/categories")
@Tag(
    name = "Category Internal",
    description = "Internal category controller",
)
class CategoryCommandController(
    private val productCategoryService: ProductCategoryService,
) {
    @PostMapping
    @Operation(summary = "카테고리 생성")
    fun createCategory(
        @RequestBody command: Request.CreateCategory,
    ): ResponseEntity<Unit> {
        productCategoryService.createCategory(command)

        return ResponseEntity.noContent().build()
    }

    @PatchMapping
    @Operation(summary = "카테고리 수정")
    fun updateCategory(
        @RequestBody command: Request.UpdateCategory,
    ): ResponseEntity<Unit> {
        productCategoryService.updateCategoryUseCase(command)

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카테고리 삭제")
    fun deleteCategory(
        @PathVariable("id") id: Long,
    ): ResponseEntity<Unit> {
        productCategoryService.deleteCategory(id)

        return ResponseEntity.noContent().build()
    }
}
