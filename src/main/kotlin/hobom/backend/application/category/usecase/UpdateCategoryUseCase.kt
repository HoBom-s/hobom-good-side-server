package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.dto.command.UpdateCategory
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.CommandBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("updateCategoryUseCase")
class UpdateCategoryUseCase(
    private val commandBus: CommandBus,
) : UseCase<Request.UpdateCategory, Response.Empty> {
    override fun execute(request: Request.UpdateCategory): Response.Empty {
        val command = UpdateCategory(
            id = request.id,
            title = request.title,
            path = request.path,
            sortIndex = request.sortIndex,
        )

        commandBus.execute<UpdateCategory, Empty>(command)

        return Response.Empty()
    }
}
