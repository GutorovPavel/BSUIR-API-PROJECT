package com.example.bsuirmentors.data.remote.dto.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("auth_token")
    var token: String,

    @SerializedName("user")
    var user: AuthUserDto
)
