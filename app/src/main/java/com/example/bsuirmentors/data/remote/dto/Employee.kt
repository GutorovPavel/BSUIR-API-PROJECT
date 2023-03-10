package com.example.bsuirmentors.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Employee(
    val calendarId: String,
    val degree: String,
    val degreeAbbrev: String,
    val department: Any,
    val email: Any,
    val firstName: String,
    val id: Int,
    val jobPositions: Any,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String,
    val urlId: String
)