package com.example.bsuirmentors.domain.models

import com.example.bsuirmentors.data.remote.dto.gradeBook.Student

data class GradeBook(
    val student: Student,
    val students: List<Student>
)
