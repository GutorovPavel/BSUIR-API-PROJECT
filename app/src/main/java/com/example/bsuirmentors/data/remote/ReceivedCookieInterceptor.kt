package com.example.bsuirmentors.data.remote

import android.util.Log
import com.example.bsuirmentors.data.local.IISDao
import com.example.bsuirmentors.data.local.entities.CookieEntity
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.Response


class ReceivedCookieInterceptor(
//    private val sessionManager: SessionManager
    private val dao: IISDao
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {

            val cookies = HashSet<String>()
            for(header in originalResponse.headers("Set-Cookie"))
                cookies.add(header)

            runBlocking {
                withContext(Dispatchers.IO) {
                    dao.setCookie(CookieEntity(cookies.toString().drop(1).dropLast(1)))
                }
            }
//            sessionManager.saveCookie(cookies)
//            Log.e("Main", sessionManager.fetchCookie()?.first().toString())
        }
        return originalResponse
    }
}