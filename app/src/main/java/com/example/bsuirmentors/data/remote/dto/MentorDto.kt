package com.example.bsuirmentors.data.remote.dto

import com.example.bsuirmentors.domain.models.Mentor


data class MentorDto(
    val academicDepartment: List<String>,
    val calendarId: String,
    val degree: String,
    val fio: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String,
    val urlId: String
)

fun MentorDto.toMentor(): Mentor {
    return Mentor(
        academicDepartment,
        degree,
        firstName,
        lastName,
        middleName,
        photoLink,
        rank,
        urlId
    )
}