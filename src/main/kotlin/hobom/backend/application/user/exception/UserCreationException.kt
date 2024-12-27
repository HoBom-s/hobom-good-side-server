package hobom.backend.application.user.exception

import hobom.backend.global.BaseException
import org.springframework.http.HttpStatus

class UserCreationException(message: String) : BaseException(HttpStatus.INTERNAL_SERVER_ERROR, message)
