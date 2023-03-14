package com.example.bsuirmentors.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bsuirmentors.domain.models.Mentor

@Entity
data class MentorEntity(
    val academicDepartment: List<String>,
    //    val calendarId: String,
    val degree: String,
    //    val fio: String,
    val firstName: String,
    //    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String? = "",
    val rank: String? = "",
    val urlId: String,
    @PrimaryKey val id: Int? = null
)

fun MentorEntity.toMentor(): Mentor {
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
fun Mentor.toMentorEntity(): MentorEntity {
    return MentorEntity(
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
