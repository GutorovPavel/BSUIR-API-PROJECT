package com.example.bsuirmentors.data.util

import android.content.Context
import android.content.SharedPreferences
import com.example.bsuirmentors.common.Constants

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context
        .getSharedPreferences(Constants.APP, Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        prefs.edit()
            .putString(USER_TOKEN, token)
            .apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}