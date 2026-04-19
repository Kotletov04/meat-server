package com.example
import JWT
import SecurityServiceImpl
import com.example.repository.AuthRepositoryImpl
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import repository.AuthRepository
import security.SecurityService
import security.TokenService
import usecases.RegistrationUseCase

@Configuration
class App {

    @Bean
    fun provideTokenService(): TokenService = JWT()

    @Bean
    fun provideSecurityService(): SecurityService = SecurityServiceImpl()

    @Bean
    fun authRepository(): AuthRepository = AuthRepositoryImpl()

    @Bean
    fun registrationUseCase(
        authRepository: AuthRepository,
        securityService: SecurityService,
        tokenService: TokenService
    ): RegistrationUseCase {
        return RegistrationUseCase(
            authRepository = authRepository,
            securityService = securityService,
            tokenService = tokenService,
        )
    }

}