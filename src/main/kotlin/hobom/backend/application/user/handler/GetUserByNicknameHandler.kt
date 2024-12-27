package hobom.backend.application.user.handler

import hobom.backend.application.user.dto.query.GetUserByNickname
import hobom.backend.application.user.dto.query.UserQueryResponse
import hobom.backend.common.handler.QueryHandler
import hobom.backend.domain.repository.user.UserRepository
import org.springframework.stereotype.Component

@Component
class GetUserByNicknameHandler(
    private val userRepository: UserRepository,
) : QueryHandler<GetUserByNickname, UserQueryResponse?> {
    override fun handle(query: GetUserByNickname): UserQueryResponse? {
        val user = userRepository.findByNickname(query.nickname)
            ?: return null

        return UserQueryResponse(
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
