package hobom.backend.presentation.auth.query

import hobom.backend.application.user.dto.usecase.Request
import hobom.backend.application.user.dto.usecase.Response
import hobom.backend.common.auth.AccessToken
import hobom.backend.common.auth.JwtTokenProvider
import hobom.backend.common.usecase.UseCase
import hobom.backend.presentation.ExternalPrefix
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.naming.AuthenticationException

@RestController
@RequestMapping("$ExternalPrefix/auth")
@Tag(
    name = "Auth External",
    description = "External Auth controller",
)
class AuthQueryController(
    private val jwtTokenProvider: JwtTokenProvider,
    private val getUserByNicknameUseCase: UseCase<Request.GetUserByNickname, Response.GetUserByNicknameResponse>,
) {
    @GetMapping("/me")
    @Operation(summary = "사용자 토큰으로 정보 조회")
    fun getMe(
        accessToken: AccessToken,
    ): ResponseEntity<Response.GetUserByNicknameResponse> {
        val nickname = jwtTokenProvider.getUserNicknameFromToken(accessToken.token)
            ?: throw AuthenticationException("사용자 토큰이 유효하지 않아요.")
        val userResponse = getUserByNicknameUseCase.execute(Request.GetUserByNickname(nickname))

        return ResponseEntity.ok(userResponse)
    }
}
