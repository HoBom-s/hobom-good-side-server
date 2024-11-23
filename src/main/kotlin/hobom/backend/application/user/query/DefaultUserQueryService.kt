package hobom.backend.application.user.query

import hobom.backend.application.user.dto.UserResponse
import hobom.backend.domain.model.user.User
import hobom.backend.domain.repository.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class DefaultUserQueryService(
    private val userRepository: UserRepository,
) : UserQueryService {
    override fun searchUserById(id: Long): UserResponse {
        val userResponse = with(this) {
            val user = findById(id)

            UserResponse.from(user = user)
        }

        return userResponse
    }

    override fun searchUserByNickname(nickname: String): UserResponse {
        val foundUser = with(userRepository) {
            val user = findByNickname(nickname = nickname)
                ?: throw NotFoundException()

            UserResponse.from(user = user)
        }

        return foundUser
    }

    private fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            throw NotFoundException()
        }
    }
}
