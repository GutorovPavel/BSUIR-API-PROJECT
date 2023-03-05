package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto

interface IISRepository {

    suspend fun getMentors(): List<MentorDto>
    suspend fun getGroups(): List<GroupDto>
}