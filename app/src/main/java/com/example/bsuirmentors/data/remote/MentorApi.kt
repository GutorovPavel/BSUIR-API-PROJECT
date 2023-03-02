package com.example.bsuirmentors.data.remote

import com.example.bsuirmentors.data.remote.dto.MentorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MentorApi {

    @GET("v1/employees/all")
    suspend fun getMentors(): List<MentorDto>

//    @GET("/v1/employees/schedule/{urlId}}")
//    suspend fun getMentorById(@Path("urlId") urlId: String): MentorDto
}