package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity
import com.example.bsuirmentors.data.remote.dto.*
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.login.LoginResponse
import retrofit2.Call

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

    //User
    suspend fun login(loginRequest: LoginRequest): AuthUserDto

}