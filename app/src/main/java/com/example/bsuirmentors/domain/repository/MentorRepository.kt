package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.remote.dto.MentorDto

interface MentorRepository {

    suspend fun getMentors(): List<MentorDto>

}