package com.example.bsuirmentors.domain.models

data class Mentor(
    val academicDepartment: List<String>,
//    val calendarId: String,
    val degree: String,
//    val fio: String,
    val firstName: String,
//    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String? = "",
    val urlId: String
)