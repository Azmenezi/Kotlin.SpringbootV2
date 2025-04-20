package com.coded.spring.ordering.authentication.jwt

import org.springframework.security.authentication.*
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {

    @GetMapping("/test")
    fun testing() = "test"

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse {
        println("🔐 Received login request: $authRequest")

        val authToken = UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password)
        println("🔐 Created UsernamePasswordAuthenticationToken")

        val authentication = try {
            authenticationManager.authenticate(authToken)
        } catch (ex: Exception) {
            println("❌ Authentication failed: ${ex.message}")
            throw UsernameNotFoundException("Invalid credentials")
        }

        println("✅ Authentication success: ${authentication.isAuthenticated}")
        println("➡️ Authorities: ${authentication.authorities}")

        if (authentication.isAuthenticated) {
            val userDetails = userDetailsService.loadUserByUsername(authRequest.username)
            println("✅ Loaded user details for: ${userDetails.username}")

            val token = jwtService.generateToken(userDetails.username)
            println("🔑 Generated JWT token: $token")

            return AuthenticationResponse(token)
        } else {
            println("❌ Authentication marked as not authenticated")
            throw UsernameNotFoundException("Invalid user request!")
        }

    }
}

data class AuthenticationRequest(
    val username: String,
    val password: String
)

data class AuthenticationResponse(
    val token: String
)