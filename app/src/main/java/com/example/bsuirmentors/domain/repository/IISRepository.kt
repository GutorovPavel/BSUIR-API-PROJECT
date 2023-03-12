package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import com.example.bsuirmentors.data.remote.dto.StudentGroup
import com.example.bsuirmentors.domain.models.Group

interface IISRepository {

    suspend fun getMentors(): List<MentorDto>
    suspend fun getGroups(): List<GroupDto>
//    suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto
    suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto

}