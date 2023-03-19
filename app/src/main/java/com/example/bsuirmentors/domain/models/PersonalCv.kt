package com.example.bsuirmentors.domain.models

import com.example.bsuirmentors.data.remote.dto.profile.Reference
import com.example.bsuirmentors.data.remote.dto.profile.Skill

data class PersonalCv (
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
    val references: List<Reference>? = emptyList(),
    val searchJob: Boolean,
    val showRating: Boolean,
    val skills: List<Skill>? = emptyList(),
    val speciality: String,
    val studentGroup: String,
    val summary: String? = ""
)
