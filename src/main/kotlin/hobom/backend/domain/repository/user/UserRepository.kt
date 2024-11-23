package hobom.backend.domain.repository.user

import hobom.backend.domain.model.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByNickname(nickname: String): User?
}
