package com.example.bsuirmentors.presentation.personalCabinet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.domain.usecases.GetPersonalCvUseCase
import com.example.bsuirmentors.domain.usecases.LogoutUseCase
import com.example.bsuirmentors.presentation.personalCabinet.profile.PersonalCvState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalCabinetViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
): ViewModel() {

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
        }
    }
}