package hobom.backend.common.usecase

interface UseCase<in Request, out Response> {
    fun execute(request: Request): Response?
}
