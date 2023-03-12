package com.example.bsuirmentors.data.remote

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import com.example.bsuirmentors.data.remote.dto.ScheduleDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IISApi {

    @GET("v1/employees/all")
    suspend fun getMentors(): List<MentorDto>

    @GET("v1/student-groups")
    suspend fun getGroups(): List<GroupDto>

    @GET("v1/schedule")
    suspend fun getScheduleByGroup(@Query("studentGroup") studentGroup: String): ScheduleDto

//    @GET("v1/schedule?studentGroup=053501")
//    suspend fun getScheduleByGroup(): ScheduleDto

//    @GET("/v1/employees/schedule/{urlId}}")
//    suspend fun getMentorById(@Path("urlId") urlId: String): MentorDto
}