package com.example.bsuirmentors.presentation.personalCabinet.gradeBook

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.usecases.GetGradeBookUseCase
import com.example.bsuirmentors.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GradeBookViewModel @Inject constructor(
    private val getGradeBookUseCase: GetGradeBookUseCase
): ViewModel() {

    private val _state = mutableStateOf(GradeBookState())
    val state: State<GradeBookState> = _state

    init {
        getGradeBook()
    }

    private fun getGradeBook() {
        getGradeBookUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = GradeBookState( gradeBook = it.data )
                }
                is Resource.Error -> {
                    _state.value = GradeBookState (
                        error = it.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = GradeBookState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}