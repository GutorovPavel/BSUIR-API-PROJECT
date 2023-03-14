package com.example.bsuirmentors.data.remote.dto


import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.domain.models.Group
import com.google.gson.annotations.SerializedName

data class GroupDto(
    val calendarId: String,
    val course: Int,
    val educationDegree: Int,
    val facultyAbbrev: String,
    val facultyId: Int,
    val id: Int,
    val name: String,
    val specialityAbbrev: String,
    val specialityDepartmentEducationFormId: Int,
    val specialityName: String
)

fun GroupDto.toGroup(): Group {
    return Group(name)
}
