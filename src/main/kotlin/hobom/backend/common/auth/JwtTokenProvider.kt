package hobom.backend.common.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import hobom.backend.domain.model.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

@Component
class JwtTokenProvider {
    companion object {
        private const val ACCESS_EXPIRE_TIME = 1000L * 60L * 60L * 1L // 1 hour
        private const val REFRESH_EXPIRE_TIME = 1000L * 60L * 60L * 24L // 24 hours
        private const val SECRET_KEY = "rmsiddkanrjsksjgrhdlfeksehfflrhtlvekrmsiddkanrjsksjgrhdlfeksehfflrhtlvek"
        private val signingKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
        private val objectMapper = ObjectMapper().apply {
            registerModule(JavaTimeModule())
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        }
    }

    fun generateAccessToken(user: User): String {
        return generateToken(
            user = user,
            expirationTime = ACCESS_EXPIRE_TIME,
        )
    }

    fun generateRefreshToken(user: User): String {
        return generateToken(
            user = user,
            expirationTime = REFRESH_EXPIRE_TIME,
        )
    }

    fun getUserNicknameFromToken(token: String): String? {
        val claims = parseToken(token)

        return claims["nickname"] as? String
    }

    fun isExpired(token: String): Boolean {
        val claims = Jwts
            .parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .payload
        val expiration = claims.expiration
            ?: throw IllegalAccessException("토큰이 정의되어 있지 않아요.")

        return expiration.before(Date())
    }

    private fun generateToken(user: User, expirationTime: Long): String {
        val now = Date()
        val expiration = Date(now.time + expirationTime)
        val claims = generateClaims(
            user = user,
            now = now,
            expiration = expiration,
        )

        return Jwts.builder()
            .claims(claims)
            .subject(user.nickname)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(signingKey)
            .compact()
    }

    private fun generateClaims(user: User, now: Date, expiration: Date): Map<String, String> {
        val nowLocalDateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault())
        val expirationLocalDateTime = LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.systemDefault())

        return mapOf(
            "nickname" to user.nickname,
            "issuedAt" to objectMapper.writeValueAsString(nowLocalDateTime),
            "expiredAt" to objectMapper.writeValueAsString(expirationLocalDateTime),
        )
    }

    private fun parseToken(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
