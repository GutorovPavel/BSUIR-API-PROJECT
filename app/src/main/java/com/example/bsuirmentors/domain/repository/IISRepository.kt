package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity
import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import com.example.bsuirmentors.data.remote.dto.StudentGroup
import com.example.bsuirmentors.domain.models.Group

interface IISRepository {

    //Groups
    suspend fun getGroups(): List<GroupDto>
    suspend fun insertGroupsToLocal(groups: List<GroupEntity>)
    suspend fun getGroupsFromLocal(): List<GroupEntity>
    suspend fun clearGroupsFromLocal()

    //Mentors
    suspend fun getMentors(): List<MentorDto>
    suspend fun insertMentorsToLocal(mentors: List<MentorEntity>)
    suspend fun getMentorsFromLocal(): List<MentorEntity>
    suspend fun clearMentorsFromLocal()

    //Schedule
    suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto

}