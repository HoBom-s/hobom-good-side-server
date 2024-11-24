package hobom.backend.presentation.user.query

import hobom.backend.application.user.dto.UserResponse
import hobom.backend.application.user.query.UserQueryService
import hobom.backend.presentation.InternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$InternalPrefix/users")
@Tag(
    name = "User External",
    description = "External User Controller",
)
class UserQueryController(
    private val userQueryService: UserQueryService,
) {
    @GetMapping
    @Operation(summary = "모든 유저 정보 조회 페이지네이션")
    fun getUsers(
        @PageableDefault(
            size = 30,
            page = 0,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC,
        ) pageable: Pageable,
    ): ResponseEntity<Page<UserResponse>> {
        val userResponse = userQueryService.findAll(
            pageable = pageable,
        )

        return ResponseEntity.ok(userResponse)
    }
}
