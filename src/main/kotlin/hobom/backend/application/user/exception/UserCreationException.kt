package hobom.backend.application.user.exception

class UserCreationException(message: String, e: Exception) : RuntimeException(message, e)
