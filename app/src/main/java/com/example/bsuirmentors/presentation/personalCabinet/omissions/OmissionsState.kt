package com.example.bsuirmentors.presentation.personalCabinet.omissions

import com.example.bsuirmentors.data.remote.dto.omissions.OmissionDto

data class OmissionsState (
    val isLoading: Boolean = false,
    val omission: OmissionDto? = null,
    val error: String = ""
)