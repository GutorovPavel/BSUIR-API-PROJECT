package com.example.bsuirmentors.presentation.scheduleCabinet.schedule

import com.example.bsuirmentors.domain.models.Schedule

data class ScheduleState(
    val isLoading: Boolean = false,
    val schedule: Schedule? = null,
    val error: String = ""
)