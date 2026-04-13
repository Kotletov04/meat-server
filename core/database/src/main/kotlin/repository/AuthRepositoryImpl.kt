package com.example.repository

import Database
import Status
import com.example.entity.AuthEntity
import models.RegistrationModel
import org.hibernate.Transaction
import repository.AuthRepository

class AuthRepositoryImpl: AuthRepository {

    override fun register(registrationModel: RegistrationModel): Status<Boolean> {
        val sessionObject = Database.sessionFactory.openSession()
        val transaction = sessionObject.beginTransaction()
        try {
            val authEntity = AuthEntity.fromRegistrationModel(registrationModel = registrationModel)
            sessionObject.persist(authEntity)
            transaction.commit()
            return Status.Success(true)
        } catch(e: Exception) {
            transaction.rollback()
            return Status.Error("", false, Errors.TestError)
        } finally {
            sessionObject.close()
        }

    }
}
