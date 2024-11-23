package hobom.backend.config

import hobom.backend.helper.JwtHelper
import hobom.backend.utils.ObjectUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter: OncePerRequestFilter() {
    private val HEADER_KEY = "Authorization"
    private val PREFIX = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token = validateTokenFromRequest(request)

            if (token != null && !JwtHelper.isExpired(token)) {
                val auth = JwtHelper.getAuthentication(token)

                if (auth.isAuthenticated) {
                    val authorities = auth.authorities
                    val authenticationWithAuthorities = UsernamePasswordAuthenticationToken(
                        auth.principal,
                        null,
                        authorities
                    )

                    SecurityContextHolder.getContext().authentication = authenticationWithAuthorities
                }
            }
        } catch (e: Exception) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed !")

            return
        }

        filterChain.doFilter(request, response)
    }

    private fun validateTokenFromRequest(
        request: HttpServletRequest,
    ): String? {
        val token = request.getHeader(HEADER_KEY)

        if(!ObjectUtils.isEmpty(token) && token.startsWith(PREFIX)) {
            return token.substring(PREFIX.length)
        }

        return null
    }
}