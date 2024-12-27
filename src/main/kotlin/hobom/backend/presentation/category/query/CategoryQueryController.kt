package hobom.backend.presentation.category.query

import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.application.category.services.ProductCategoryService
import hobom.backend.common.query.ListResponse
import hobom.backend.presentation.ExternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$ExternalPrefix/categories")
@Tag(
    name = "Category External",
    description = "External category controller",
)
class CategoryQueryController(
    private val productCategoryService: ProductCategoryService,
) {
    @GetMapping
    @Operation(summary = "카테고리 모두 조회")
    fun findCategoryAll(): ResponseEntity<ListResponse<Response.FindCategoryDetail>> {
        val categories = productCategoryService.findCategoryAll()

        return ResponseEntity.ok(categories)
    }

    @GetMapping("/{id}")
    @Operation(summary = "카테고리 ID로 단건 조회")
    fun findCategoryDetail(
        @PathVariable("id") id: Long,
    ): ResponseEntity<Response.FindCategoryDetail> {
        val response = productCategoryService.findCategoryDetail(id)

        return ResponseEntity.ok(response)
    }
}
