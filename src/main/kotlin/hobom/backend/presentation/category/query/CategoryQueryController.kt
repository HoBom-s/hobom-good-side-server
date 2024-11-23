package hobom.backend.presentation.category.query

import hobom.backend.application.category.dto.CategoryResponse
import hobom.backend.application.category.query.CategoryQueryService
import hobom.backend.presentation.ExternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$ExternalPrefix/category")
@Tag(
    name = "Category External",
    description = "External category controller",
)
class CategoryQueryController(
    private val categoryQueryService: CategoryQueryService,
) {
    @GetMapping
    @Operation(summary = "카테고리 모두 조회")
    fun findAll(): ResponseEntity<List<CategoryResponse>> {
        val categories = categoryQueryService.findAll()

        return ResponseEntity.ok(categories)
    }
}
