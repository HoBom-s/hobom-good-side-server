package hobom.backend.application.category.usecase

import hobom.backend.application.category.dto.command.DeleteCategory
import hobom.backend.application.category.dto.command.Empty
import hobom.backend.application.category.dto.usecase.Request
import hobom.backend.application.category.dto.usecase.Response
import hobom.backend.common.bus.CommandBus
import hobom.backend.common.usecase.UseCase
import org.springframework.stereotype.Component

@Component("deleteCategoryUseCase")
class DeleteCategoryUseCase(
    private val commandBus: CommandBus,
) : UseCase<Request.DeleteCategory, Response.Empty> {
    override fun execute(request: Request.DeleteCategory): Response.Empty {
        val command = DeleteCategory(request.id)

        commandBus.execute<DeleteCategory, Empty>(command)

        return Response.Empty()
    }
}
