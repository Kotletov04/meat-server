package usecases

import Status
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import models.RegistrationModel
import repository.AuthRepository
import security.SecurityService
import security.TokenService


class RegistrationUseCase(
    private val authRepository: AuthRepository,
    private val securityService: SecurityService,
    private val tokenService: TokenService
) {

    operator suspend fun invoke(username: String, password: String, email: String): Status<Boolean> {
        try {
            val hashPassword = securityService.hashingPassword(password = password)
            val pairToken = tokenService.generateRefreshToken()
            val refreshToken = pairToken.data!!.first
            val lifetimeToken = pairToken.data!!.second
            val registrationModel = RegistrationModel(
                username = username,
                email = email,
                passwordHash = hashPassword,
                refresh = refreshToken,
                exp = lifetimeToken
            )
            val registrationStatus = authRepository.register(registrationModel = registrationModel)
            return Status.Success(true, )
        } catch (e: Exception) {
            return Status.Error(message = e.message, typeError = Errors.TestError)
        }

    }


}
