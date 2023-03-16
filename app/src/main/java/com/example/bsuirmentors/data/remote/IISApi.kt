package com.example.bsuirmentors.data.remote

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IISApi {

    @GET("v1/employees/all")
    suspend fun getMentors(): List<MentorDto>

    @GET("v1/student-groups")
    suspend fun getGroups(): List<GroupDto>

    @GET("v1/schedule")
    suspend fun getScheduleByGroup(@Query("studentGroup") studentGroup: String): ScheduleDto

    @POST("v1/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthUserDto

}