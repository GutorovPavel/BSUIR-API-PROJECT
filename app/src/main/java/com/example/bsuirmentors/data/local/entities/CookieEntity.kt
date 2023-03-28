package com.example.bsuirmentors.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CookieEntity(
    val cookie: String?,
    @PrimaryKey val id: Int? = 0
)
