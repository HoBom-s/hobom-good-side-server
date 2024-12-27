package hobom.backend.application.user.handler

import hobom.backend.application.user.dto.command.CreateUser
import hobom.backend.application.user.dto.command.Empty
import hobom.backend.application.user.exception.UserCreationException
import hobom.backend.common.handler.CommandHandler
import hobom.backend.domain.model.user.User
import hobom.backend.domain.repository.user.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CreateUserHandler(
    private val userRepository: UserRepository,
) : CommandHandler<CreateUser, Empty> {
    @Transactional
    override fun handle(command: CreateUser): Empty {
        try {
            userRepository.save(command.toEntity())
        } catch (e: Exception) {
            throw UserCreationException("사용자를 생성하는 도중 오류가 발생했어요.")
        }

        return Empty()
    }

    private fun CreateUser.toEntity(): User {
        return User(
            seasonId = this.seasonId,
            nickname = this.nickname,
            password = this.password,
            name = this.name,
            role = this.role,
        )
    }
}
