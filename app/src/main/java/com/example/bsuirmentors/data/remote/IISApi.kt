package com.example.bsuirmentors.data.remote

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import com.example.bsuirmentors.data.remote.dto.gradeBook.GradeBookDto
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.omissions.OmissionDto
import com.example.bsuirmentors.data.remote.dto.profile.PersonalCvDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IISApi {

    @GET("v1/employees/all")
    suspend fun getMentors(): List<MentorDto>

    @GET("v1/student-groups")
    suspend fun getGroups(): List<GroupDto>

    @GET("v1/schedule")
    suspend fun getScheduleByGroup(@Query("studentGroup") studentGroup: String): ScheduleDto

    @GET("v1/schedule/current-week")
    suspend fun getCurrentWeek(): Int

    @POST("v1/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthUserDto

    @GET("v1/auth/logout")
    suspend fun logout(@Header("Cookie") cookie: String)

    @GET("v1/profiles/personal-cv")
    suspend fun getPersonalCv(@Header("Cookie") cookie: String?): PersonalCvDto

    @GET("v1/grade-book")
    suspend fun getGradeBook(@Header("Cookie") cookie: String?): GradeBookDto

    @GET("v1/omissions-by-student")
    suspend fun getOmissions(@Header("Cookie") cookie: String?): OmissionDto

}