package com.example.bsuirmentors.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bsuirmentors.data.remote.dto.login.LoginRequest
import com.example.bsuirmentors.domain.repository.IISRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: IISRepository
): ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun saveAuthToken(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            val user = repository.login(LoginRequest(username, password))
            _state.value = LoginState(user = user)


//            repository.login(LoginRequest(username, password))
//                .enqueue(object : Callback<LoginResponse> {
//                    override fun onResponse(
//                        call: Call<LoginResponse>,
//                        response: Response<LoginResponse>
//                    ) {
//                        val loginResponse = response.body()
//                        if (loginResponse != null) {
//                            Log.e("status", loginResponse.user.email)
//                        } else {
//                            Log.e("response", "error")
//                        }
//
//
//                        if (loginResponse?.statusCode == 200) {
//                            Log.e("Main", loginResponse.authToken)
//                            sessionManager.saveAuthToken(loginResponse.authToken)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                        //Error
//                    }
//                })
        }
    }
}