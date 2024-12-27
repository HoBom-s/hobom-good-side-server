package hobom.backend.application.user.dto.usecase

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
)
