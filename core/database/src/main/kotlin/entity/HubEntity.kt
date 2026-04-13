package com.example.entity

import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "hubs")
class HubEntity(
    val hubName: String,
    val type: String,
    val isOpen: Boolean = true,
    val amountUsers: Int,
    val admin: String,
    val description: String,
    /*@OneToMany(
        mappedBy = "hubs",
        fetch =
    )*/
    val users: MutableList<UserEntity>
): BaseEntity<Long>() {

}