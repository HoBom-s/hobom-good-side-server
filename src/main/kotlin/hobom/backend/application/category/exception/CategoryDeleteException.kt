package hobom.backend.application.category.exception

import hobom.backend.global.BaseException
import org.springframework.http.HttpStatus

class CategoryDeleteException(message: String) : BaseException(HttpStatus.INTERNAL_SERVER_ERROR, message)
