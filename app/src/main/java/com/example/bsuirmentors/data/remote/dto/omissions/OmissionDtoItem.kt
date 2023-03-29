package com.example.bsuirmentors.data.remote.dto.omissions


import com.google.gson.annotations.SerializedName

data class OmissionDtoItem(
    val dateFrom: Long,
    val dateTo: Long,
    val id: Int,
    val name: String,
    val note: String? = "",
    val term: String
)