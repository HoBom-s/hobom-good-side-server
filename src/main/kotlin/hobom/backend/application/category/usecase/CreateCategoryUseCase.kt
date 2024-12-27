package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.command.CreateCategory
import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.CommandBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("createCategoryUseCase")
class CreateCategoryUseCase(
    private val commandBus: CommandBus,
) : UseCase<Request.CreateCategory, Response.Empty> {
    override fun execute(request: Request.CreateCategory): Response.Empty {
        val command = CreateCategory(
            title = request.title,
            path = request.path,
            sortIndex = request.sortIndex,
        )

        commandBus.execute<CreateCategory, Empty>(command)

        return Response.Empty()
    }
}
