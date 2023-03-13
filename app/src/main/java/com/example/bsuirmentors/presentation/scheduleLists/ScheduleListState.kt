package com.example.bsuirmentors.presentation.scheduleLists

import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor


data class ScheduleListState(
    val isLoading: Boolean = false,
    val mentors: List<Mentor> = emptyList(),
    val groups: List<Group> = emptyList(),
    val error: String = ""
)