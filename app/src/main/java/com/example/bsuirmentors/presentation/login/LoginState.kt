package com.example.bsuirmentors.presentation.login

import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto


data class LoginState(
    val isLoading: Boolean = false,
    val user: AuthUserDto? = null,
    val error: String = ""
)