package controllers

import Status
import models.LoginDto
import models.RegistrationDto
import models.UserDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import usecases.RegistrationUseCase
import kotlin.coroutines.CoroutineContext


@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val registrationUseCase: RegistrationUseCase
) {

    @PostMapping("/login")
    suspend fun login(@RequestBody loginBody: LoginDto): Status<UserDto> {
        val test =
        return Status.Success(data = UserDto(id = 1))
    }

    @PostMapping("/register")
    suspend fun register(@RequestBody registrationBody: RegistrationDto): Status<UserDto> {
        val registrationUseCase = registrationUseCase(
            username = registrationBody.username,
            password = registrationBody.password,
            email = registrationBody.email
        )
        return Status.Success(UserDto(id = 0))
    }

}