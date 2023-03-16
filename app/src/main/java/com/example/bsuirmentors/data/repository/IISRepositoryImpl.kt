package com.example.bsuirmentors.data.repository

import com.example.bsuirmentors.data.local.IISDao
import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity
import com.example.bsuirmentors.data.remote.IISApi
import com.example.bsuirmentors.data.remote.dto.*
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.login.LoginResponse
import com.example.bsuirmentors.domain.repository.IISRepository
import retrofit2.Call
import javax.inject.Inject

class IISRepositoryImpl @Inject constructor(
    private val api: IISApi,
    private val dao: IISDao
): IISRepository {

    //Groups
    override suspend fun getGroups(): List<GroupDto> {
        return api.getGroups()
    }

    override suspend fun insertGroupsToLocal(groups: List<GroupEntity>) {
        dao.insertGroups(groups)
    }

    override suspend fun getGroupsFromLocal(): List<GroupEntity> {
        return dao.getGroups()
    }

    override suspend fun clearGroupsFromLocal() {
        dao.clearGroups()
    }

    //Mentors
    override suspend fun getMentors(): List<MentorDto> {
        return api.getMentors()
    }

    override suspend fun insertMentorsToLocal(mentors: List<MentorEntity>) {
        dao.insertMentors(mentors)
    }

    override suspend fun getMentorsFromLocal(): List<MentorEntity> {
        return dao.getMentors()
    }

    override suspend fun clearMentorsFromLocal() {
        dao.clearMentors()
    }

    //Schedule
    override suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto {
        return api.getScheduleByGroup(studentGroup)
    }

    override suspend fun login(loginRequest: LoginRequest): AuthUserDto {
        return api.login(loginRequest)
    }
}