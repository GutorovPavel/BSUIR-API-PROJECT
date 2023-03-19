package com.example.bsuirmentors.data.util

import android.content.Context
import android.content.SharedPreferences
import com.example.bsuirmentors.common.Constants

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context
        .getSharedPreferences(Constants.COOKIE_PREFS, Context.MODE_PRIVATE)

    companion object {
        const val CURRENT_COOKIE = "current_cookie"
    }

    fun saveCookie(cookie: HashSet<String>) {
        prefs.edit()
            .putStringSet(CURRENT_COOKIE, cookie)
            .apply()
    }

    fun fetchCookie(): MutableSet<String>? {
        return prefs.getStringSet(CURRENT_COOKIE, null)
    }
}