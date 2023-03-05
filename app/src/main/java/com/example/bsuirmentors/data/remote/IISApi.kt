package com.example.bsuirmentors.data.remote

import com.example.bsuirmentors.data.remote.dto.GroupDto
import com.example.bsuirmentors.data.remote.dto.MentorDto
import retrofit2.http.GET

interface IISApi {

    @GET("v1/employees/all")
    suspend fun getMentors(): List<MentorDto>

    @GET("v1/student-groups")
    suspend fun getGroups(): List<GroupDto>

//    @GET("/v1/employees/schedule/{urlId}}")
//    suspend fun getMentorById(@Path("urlId") urlId: String): MentorDto
}