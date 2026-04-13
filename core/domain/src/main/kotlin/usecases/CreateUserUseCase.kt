package usecases

import repository.UserRepository

class CreateUserUseCase(private val userRepository: UserRepository) {

    operator suspend fun invoke(): Status<>

}