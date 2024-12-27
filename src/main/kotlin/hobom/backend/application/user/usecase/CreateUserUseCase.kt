package hobom.backend.application.user.usecase

import hobom.backend.application.user.dto.command.CreateUser
import hobom.backend.application.user.dto.command.Empty
import hobom.backend.application.user.dto.usecase.Request
import hobom.backend.application.user.dto.usecase.Response
import hobom.backend.common.bus.CommandBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("createUserUseCase")
class CreateUserUseCase(
    private val commandBus: CommandBus,
) : UseCase<Request.CreateUserRequest, Response.Empty> {
    override fun execute(request: Request.CreateUserRequest): Response.Empty {
        val command = CreateUser(
            seasonId = request.seasonId,
            name = request.name,
            nickname = request.nickname,
            role = request.role,
            password = request.password,
        )

        commandBus.execute<CreateUser, Empty>(command)

        return Response.Empty()
    }
}
