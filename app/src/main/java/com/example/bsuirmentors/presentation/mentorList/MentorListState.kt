package com.example.bsuirmentors.presentation.mentorList

import com.example.bsuirmentors.domain.models.Mentor


data class MentorListState(
    val isLoading: Boolean = false,
    val mentors: List<Mentor> = emptyList(),
    val error: String = ""
)