package com.example.bsuirmentors.data.remote

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.example.bsuirmentors.data.util.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import java.util.prefs.Preferences



class ReceivedCookieInterceptor(
    private val sessionManager: SessionManager
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {

            val cookies = HashSet<String>()
            for(header in originalResponse.headers("Set-Cookie"))
                cookies.add(header)

            sessionManager.saveCookie(cookies)

            Log.e("Main", sessionManager.fetchCookie()?.first().toString())
        }
        return originalResponse
    }
}