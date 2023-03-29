package com.example.bsuirmentors.presentation.personalCabinet.profile

import com.example.bsuirmentors.data.remote.dto.profile.PersonalCvDto
import com.example.bsuirmentors.domain.models.PersonalCv
import com.example.bsuirmentors.domain.models.Schedule

data class PersonalCvState(
    val isLoading: Boolean = false,
    val personalCv: PersonalCv? = null,
    val error: String = ""
)