package hobom.backend.domain.repository.user

import hobom.backend.domain.model.user.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    override fun findAll(pageable: Pageable): Page<User>

    fun findByNickname(nickname: String): User?
}
