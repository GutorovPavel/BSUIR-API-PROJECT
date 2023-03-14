package com.example.bsuirmentors.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bsuirmentors.domain.models.Group


@Entity
data class GroupEntity (
    val name: String,
    @PrimaryKey val id: Int? = null,
//    val calendarId: String,
//    val course: Int,
//    val educationDegree: Int,
//    val facultyAbbrev: String,
//    val facultyId: Int,
//    val specialityAbbrev: String,
//    val specialityDepartmentEducationFormId: Int,
//    val specialityName: String
)

fun GroupEntity.toGroup(): Group {
    return Group(name)
}
fun Group.toGroupEntity(): GroupEntity {
    return GroupEntity(name)
}