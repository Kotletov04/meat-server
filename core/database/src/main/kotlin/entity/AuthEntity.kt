package com.example.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import models.RegistrationModel

@Entity
@Table(name = "auth")
data class AuthEntity(
    @Column(unique = true)
    val email: String,
    val passwordHash: String,
    val refresh: String,
    val exp: Long
): BaseEntity<Long>() {

    companion object {
        fun fromRegistrationModel(registrationModel: RegistrationModel): AuthEntity {
            return AuthEntity(
                email = registrationModel.email,
                passwordHash = registrationModel.passwordHash,
                refresh = registrationModel.refresh,
                exp = registrationModel.exp
            )
        }
    }

}
