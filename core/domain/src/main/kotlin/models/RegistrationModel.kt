package models

data class RegistrationModel(
    val username: String,
    val email: String,
    val passwordHash: String,
    val refresh: String,
    val exp: Long
)
