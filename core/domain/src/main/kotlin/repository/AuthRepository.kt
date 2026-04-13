package repository

import Status
import models.RegistrationModel

interface AuthRepository {

    fun register(registrationModel: RegistrationModel): Status<Boolean>

}