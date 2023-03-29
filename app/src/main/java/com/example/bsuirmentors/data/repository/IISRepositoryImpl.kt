package com.example.bsuirmentors.data.repository

import com.example.bsuirmentors.data.local.IISDao
import com.example.bsuirmentors.data.local.entities.CookieEntity
import com.example.bsuirmentors.data.local.entities.GroupEntity
import com.example.bsuirmentors.data.local.entities.LoginRequestEntity
import com.example.bsuirmentors.data.local.entities.MentorEntity
import com.example.bsuirmentors.data.remote.IISApi
import com.example.bsuirmentors.data.remote.dto.*
import com.example.bsuirmentors.data.remote.dto.gradeBook.GradeBookDto
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.omissions.OmissionDto
import com.example.bsuirmentors.data.remote.dto.profile.PersonalCvDto
import com.example.bsuirmentors.domain.repository.IISRepository
import javax.inject.Inject

class IISRepositoryImpl @Inject constructor(
    private val api: IISApi,
    private val dao: IISDao,
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

    override suspend fun getCurrentWeek(): Int {
        return api.getCurrentWeek()
    }

    //Schedule
    override suspend fun getScheduleByGroup(studentGroup: String): ScheduleDto {
        return api.getScheduleByGroup(studentGroup)
    }


    //User
    override suspend fun login(loginRequest: LoginRequest): AuthUserDto {
        return api.login(loginRequest)
    }

    override suspend fun logout(cookie: String) {
        api.logout(cookie)
    }

    override suspend fun getPersonalCv(cookie: String?): PersonalCvDto {
        return api.getPersonalCv(cookie)
    }

    override suspend fun getGradeBook(cookie: String?): GradeBookDto {
        return api.getGradeBook(cookie)
    }

    override suspend fun getOmissions(cookie: String?): OmissionDto {
        return api.getOmissions(cookie)
    }

    override suspend fun getCookie(): String? {
        return dao.getCookie()?.cookie
    }

    override suspend fun setCookie(cookie: String?) {
        dao.setCookie(CookieEntity(cookie))
    }

    override suspend fun deleteCookie() {
        dao.deleteCookie()
    }

    override suspend fun getLoginAndPassword(): LoginRequestEntity? {
        return dao.getLoginAndPassword()
    }

    override suspend fun setLoginAndPassword(username: String, password: String) {
        dao.setLoginAndPassword(LoginRequestEntity(username, password))
    }

    override suspend fun deleteLoginAndPassword() {
        dao.deleteLoginAndPassword()
    }
}