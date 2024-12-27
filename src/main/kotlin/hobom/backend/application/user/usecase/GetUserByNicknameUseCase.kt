package hobom.backend.application.user.usecase

import hobom.backend.application.user.dto.query.GetUserByNickname
import hobom.backend.application.user.dto.query.UserQueryResponse
import hobom.backend.application.user.dto.usecase.Request
import hobom.backend.application.user.dto.usecase.Response
import hobom.backend.common.bus.QueryBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("getUserByNicknameUseCase")
class GetUserByNicknameUseCase(
    private val queryBus: QueryBus,
) : UseCase<Request.GetUserByNickname, Response.GetUserByNicknameResponse?> {
    override fun execute(request: Request.GetUserByNickname): Response.GetUserByNicknameResponse? {
        val query = GetUserByNickname(request.nickname)
        val user = queryBus.execute<GetUserByNickname, UserQueryResponse?>(query)
            ?: return null

        return Response.GetUserByNicknameResponse(
            id = user.id,
            seasonId = user.seasonId,
            nickname = user.nickname,
            name = user.name,
            password = user.password,
            role = user.role,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt,
        )
    }
}
