package hobom.backend.global

import hobom.backend.common.auth.AccessToken
import hobom.backend.common.auth.JwtTokenProvider
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.naming.AuthenticationException

@Component
class AccessTokenArgumentResolver(
    private val jwtTokenProvider: JwtTokenProvider,
) : HandlerMethodArgumentResolver {
    companion object {
        private const val HEADER_KEY = "Authorization"
        private const val BEARER = "Bearer "
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == AccessToken::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?,
    ): Any? {
        val authorizationHeader = webRequest.getHeader(HEADER_KEY)
        val token = authorizationHeader?.removePrefix(BEARER)

        return if (token != null) {
            if (jwtTokenProvider.isExpired(token)) {
                throw AuthenticationException("토큰이 유효하지 않아요.")
            }

            AccessToken(token)
        } else {
            null
        }
    }
}
