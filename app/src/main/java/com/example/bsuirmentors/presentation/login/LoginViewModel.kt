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

            repository.login(LoginRequest(username, password))

        }
    }
}