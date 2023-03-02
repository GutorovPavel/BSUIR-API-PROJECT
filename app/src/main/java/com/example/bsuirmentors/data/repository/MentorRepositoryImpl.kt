package com.example.bsuirmentors.data.repository

import com.example.bsuirmentors.data.remote.MentorApi
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.domain.repository.MentorRepository
import javax.inject.Inject

class MentorRepositoryImpl @Inject constructor(
    private val api: MentorApi
): MentorRepository {
    override suspend fun getMentors(): List<MentorDto> {
        return api.getMentors()
    }
}