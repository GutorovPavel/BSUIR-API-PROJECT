package com.example.bsuirmentors.domain.repository

import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.LoginRequestEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity
import com.example.bsuirmentors.data.remote.dto.*
import com.example.bsuirmentors.data.remote.dto.gradeBook.GradeBookDto
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.omissions.OmissionDto
import com.example.bsuirmentors.data.remote.dto.profile.PersonalCvDto

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
    suspend fun getCurrentWeek(): Int
    suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto

    //User
    suspend fun login(loginRequest: LoginRequest): AuthUserDto
    suspend fun logout(cookie: String)
    suspend fun getPersonalCv(cookie: String?): PersonalCvDto
    suspend fun getGradeBook(cookie: String?): GradeBookDto
    suspend fun getOmissions(cookie: String?): OmissionDto

    //Login
    suspend fun getCookie(): String?
    suspend fun setCookie(cookie: String?)
    suspend fun deleteCookie()
    suspend fun getLoginAndPassword(): LoginRequestEntity?
    suspend fun setLoginAndPassword(username: String, password: String)
    suspend fun deleteLoginAndPassword()

}