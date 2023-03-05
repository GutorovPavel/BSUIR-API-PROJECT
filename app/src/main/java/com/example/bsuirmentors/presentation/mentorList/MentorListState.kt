package com.example.bsuirmentors.presentation.mentorList

import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor


data class MentorListState(
    val isLoading: Boolean = false,
    val mentors: List<Mentor> = emptyList(),
    val groups: List<Group> = emptyList(),
    val error: String = ""
)