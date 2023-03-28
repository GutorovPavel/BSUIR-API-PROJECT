package com.example.bsuirmentors.presentation.schedule

import com.example.bsuirmentors.domain.models.Schedule

data class CurrentWeekState(
    val isLoading: Boolean = false,
    val currentWeek: Int? = null,
    val error: String = ""
)
