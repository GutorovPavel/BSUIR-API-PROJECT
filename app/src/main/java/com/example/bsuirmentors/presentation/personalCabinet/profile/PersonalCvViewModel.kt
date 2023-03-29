package com.example.bsuirmentors.presentation.personalCabinet.profile

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.data.util.SessionManager
import com.example.bsuirmentors.domain.usecases.GetPersonalCvUseCase
import com.example.bsuirmentors.domain.usecases.LogoutUseCase
import com.example.bsuirmentors.presentation.scheduleCabinet.schedule.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonalCvViewModel @Inject constructor(
    private val getPersonalCvUseCase: GetPersonalCvUseCase,
    private val logoutUseCase: LogoutUseCase
): ViewModel() {


    private val _state = mutableStateOf(PersonalCvState())
    val state: State<PersonalCvState> = _state

    init {
        getPersonalCv()
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
        }
    }

    private fun getPersonalCv() {
        getPersonalCvUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = PersonalCvState(personalCv = it.data)
                }
                is Resource.Error -> {
                    _state.value = PersonalCvState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = PersonalCvState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
