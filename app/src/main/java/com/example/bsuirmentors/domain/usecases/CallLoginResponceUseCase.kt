package com.example.bsuirmentors.domain.usecases

import android.widget.Toast
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.data.remote.dto.login.LoginResponse
import com.example.bsuirmentors.data.util.SessionManager
import com.example.bsuirmentors.domain.repository.IISRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//class CallLoginResponseUseCase @Inject constructor(
//    private val repository: IISRepository,
//) {
//
//    operator fun invoke(username: String, password: String, sessionManager: SessionManager) {
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            repository.login(LoginRequest(username, password))
//                .enqueue(object : Callback<LoginResponse> {
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val loginResponse = response.body()
//
//                        if (loginResponse?.statusCode == 200)
//                            sessionManager.saveAuthToken(loginResponse.authToken)
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        //Error
//                    }
//                })
//        }
//
//    }
//}