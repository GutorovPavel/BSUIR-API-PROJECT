package com.example.bsuirmentors.data.remote.dto


import com.google.gson.annotations.SerializedName

data class StudentGroup(
    val educationDegree: Int,
    val name: String,
    val numberOfStudents: Int,
    val specialityCode: String,
    val specialityName: String
)