package hobom.backend.application.user.dto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
)
