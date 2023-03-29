package com.example.bsuirmentors.data.remote.dto.gradeBook


import com.google.gson.annotations.SerializedName

data class GradeBookDtoItem(
    val student: Student,
    val students: List<Student>
)