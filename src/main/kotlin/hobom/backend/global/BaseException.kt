package hobom.backend.global

import org.springframework.http.HttpStatus

open class BaseException(
    val status: HttpStatus,
    override val message: String,
) : RuntimeException(message)
