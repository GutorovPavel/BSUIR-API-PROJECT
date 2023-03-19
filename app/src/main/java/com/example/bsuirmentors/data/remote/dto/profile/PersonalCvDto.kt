package com.example.bsuirmentors.data.remote.dto.profile


import com.example.bsuirmentors.domain.models.PersonalCv
import com.google.gson.annotations.SerializedName

data class PersonalCvDto(
    val birthDate: String,
    val course: Int,
    val faculty: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val middleName: String,
    val officeEmail: String,
    val officePassword: String,
    val photoUrl: String,
    val published: Boolean,
    val rating: Int,
    val references: List<Reference>,
    val searchJob: Boolean,
    val showRating: Boolean,
    val skills: List<Skill>,
    val speciality: String,
    val studentGroup: String,
    val summary: String
)

fun PersonalCvDto.toPersonalCv(): PersonalCv {
    return PersonalCv(
        birthDate,
        course,
        faculty,
        firstName,
        id,
        lastName,
        middleName,
        officeEmail,
        officePassword,
        photoUrl,
        published,
        rating,
        references,
        searchJob,
        showRating,
        skills,
        speciality,
        studentGroup,
        summary
    )
}