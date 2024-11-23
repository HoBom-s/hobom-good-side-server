package hobom.backend.application.user.query

import hobom.backend.application.user.dto.UserResponse

interface UserQueryService {
    fun searchUserById(id: Long): UserResponse

    fun searchUserByNickname(nickname: String): UserResponse
}
