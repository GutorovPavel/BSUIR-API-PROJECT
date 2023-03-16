package com.example.bsuirmentors.data.remote.dto.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class AuthUserDto(
    val canStudentNote: Boolean,
    val email: String,
    val fio: String,
    val group: String,
    val hasNotConfirmedContact: Boolean,
    val isGroupHead: Boolean,
    val phone: String,
    val photoUrl: String,
    val username: String
)
