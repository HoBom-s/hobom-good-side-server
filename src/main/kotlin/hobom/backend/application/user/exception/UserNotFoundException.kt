package hobom.backend.application.user.exception

import hobom.backend.global.BaseException
import org.springframework.http.HttpStatus

class UserNotFoundException(message: String) : BaseException(HttpStatus.NOT_FOUND, message)
