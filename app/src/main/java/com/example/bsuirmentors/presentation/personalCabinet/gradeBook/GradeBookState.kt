package com.example.bsuirmentors.presentation.personalCabinet.gradeBook

import com.example.bsuirmentors.data.remote.dto.gradeBook.GradeBookDto
import com.example.bsuirmentors.domain.models.GradeBook

data class GradeBookState (
    val isLoading: Boolean = false,
    val gradeBook: GradeBookDto? = null,
    val error: String = ""
)