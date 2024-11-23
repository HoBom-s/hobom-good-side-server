package hobom.backend.presentation.category.command

import hobom.backend.application.category.command.CategoryCommandService
import hobom.backend.application.category.dto.CategoryChangeIndexCommand
import hobom.backend.application.category.dto.CategoryCommand
import hobom.backend.application.category.dto.CategoryUpdateCommand
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
@RequestMapping("$InternalPrefix/category")
@Tag(
    name = "Category Internal",
    description = "Internal category controller",
)
class CategoryCommandController(
    private val categoryCommandService: CategoryCommandService,
) {
    @PostMapping
    @Operation(summary = "카테고리 생성")
    fun createCategory(
        @RequestBody command: CategoryCommand,
    ): ResponseEntity<Unit> {
        categoryCommandService.createCategory(
            command = command,
        )

        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}")
    @Operation(summary = "카테고리 수정")
    fun updateCategory(
        @PathVariable("id") id: Long,
        @RequestBody command: CategoryUpdateCommand,
    ): ResponseEntity<Unit> {
        categoryCommandService.updateCategory(
            id = id,
            command = command,
        )

        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{id}/sort-index")
    @Operation(summary = "카테고리 정렬 순서 변경")
    fun changeSortIndex(
        @PathVariable("id") id: Long,
        @RequestBody command: CategoryChangeIndexCommand,
    ): ResponseEntity<Unit> {
        categoryCommandService.changeSortIndex(
            id = id,
            command = command,
        )

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카테고리 삭제")
    fun deleteCategory(
        @PathVariable("id") id: Long,
    ): ResponseEntity<Unit> {
        categoryCommandService.deleteCategory(
            id = id,
        )

        return ResponseEntity.noContent().build()
    }
}
