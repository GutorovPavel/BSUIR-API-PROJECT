package com.example.bsuirmentors.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginRequestEntity(
    val username : String?,
    val password : String?,
    @PrimaryKey val id: Int? = 0
)
