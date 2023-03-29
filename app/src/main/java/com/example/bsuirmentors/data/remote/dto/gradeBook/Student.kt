package com.example.bsuirmentors.data.remote.dto.gradeBook


import com.google.gson.annotations.SerializedName

data class Student(
    val fio: String,
    val id: Int,
    val lessons: List<Lesson>,
    val subGroup: Int,
    val subGroupStudent: Int
)