package com.example.bsuirmentors.data.remote.dto.gradeBook


import com.google.gson.annotations.SerializedName

data class Lesson(
    val controlPoint: String,
    val dateString: String,
    val gradeBookOmissions: Int,
    val id: Int,
    val lessonNameAbbrev: String,
    val lessonTypeAbbrev: String,
    val lessonTypeId: Int,
    val marks: List<Int>,
    val subGroup: Int
)