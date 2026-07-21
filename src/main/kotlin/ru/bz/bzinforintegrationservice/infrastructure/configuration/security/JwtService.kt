package ru.bz.bzinforintegrationservice.infrastructure.configuration.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date
import java.util.function.Function

@Service
class JwtService(
    private val environment: Environment,
) {
    companion object {
        const val BEARER_PREFIX: String = "Bearer "
        const val HEADER_NAME: String = "Authorization"
    }

    private fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(environment.getProperty("application.token.signing.key"))
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getTokenWithoutBearer(request: HttpServletRequest): String? {
        val token = request.getHeader(HEADER_NAME)
        return token?.replace(BEARER_PREFIX, "")
    }

    fun verifyToken(token: String): Boolean {
        return !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim<Date>(token, Function<Claims, Date> { obj: Claims -> obj.expiration })
    }

    private fun <T> extractClaim(token: String, claimsResolvers: Function<Claims, T>): T {
        val claims = extractAllClaims(token)
        return claimsResolvers.apply(claims)
    }

    fun extractUsername(token: String): String {
        val claims = extractAllClaims(token)
        return claims.subject
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parser().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
            .getBody()
    }
}