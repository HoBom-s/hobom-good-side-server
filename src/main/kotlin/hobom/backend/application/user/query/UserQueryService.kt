package hobom.backend.application.user.query

import hobom.backend.application.user.dto.UserResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UserQueryService {
    fun searchUserById(id: Long): UserResponse

    fun searchUserByNickname(nickname: String): UserResponse

    fun findAll(pageable: Pageable): Page<UserResponse>
}
