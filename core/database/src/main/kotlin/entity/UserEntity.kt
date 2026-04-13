package com.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class UserEntity(

    val username: String,
    val email: String,
    val role: String,

): BaseEntity<Long>() {

}