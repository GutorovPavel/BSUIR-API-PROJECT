package com.example.bsuirmentors.data.repository

import com.example.bsuirmentors.data.remote.IISApi
import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import com.example.bsuirmentors.data.remote.dto.StudentGroup
import com.example.bsuirmentors.domain.repository.IISRepository
import javax.inject.Inject

class IISRepositoryImpl @Inject constructor(
    private val api: IISApi
): IISRepository {
    override suspend fun getMentors(): List<MentorDto> {
        return api.getMentors()
    }

    override suspend fun getGroups(): List<GroupDto> {
        return api.getGroups()
    }

//    override suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto {
//        return api.getScheduleByGroup(studentGroup)
//    }

    override suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto {
        return api.getScheduleByGroup(studentGroup)
    }
}