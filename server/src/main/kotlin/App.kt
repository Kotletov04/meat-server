package com.example

import com.example.repository.AuthRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import repository.AuthRepository
import usecases.RegistrationUseCase

@Configuration
class App(

) {

    @Bean
    fun authRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Bean
    fun registrationUseCase(authRepository: AuthRepository): RegistrationUseCase {
        return RegistrationUseCase(authRepository = authRepository)
    }

}