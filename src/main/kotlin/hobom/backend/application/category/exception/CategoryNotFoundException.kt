package hobom.backend.application.category.exception

import hobom.backend.global.BaseException
import org.springframework.http.HttpStatus

class CategoryNotFoundException(message: String) : BaseException(HttpStatus.NOT_FOUND, message)
