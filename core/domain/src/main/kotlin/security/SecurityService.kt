package security

import Status

interface SecurityService {
    fun hashingPassword(password: String, amountIterations: Int = 65536, bitsQuantity: Int = 256): String
    fun validatePassword(hashedPassword: String, password: String): Status<Boolean>
}