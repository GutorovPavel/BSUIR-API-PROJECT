package com.example.bsuirmentors.presentation.personalCabinet.omissions

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.usecases.GetOmissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OmissionsViewModel @Inject constructor(
    private val getOmissionsUseCase: GetOmissionsUseCase
): ViewModel() {

    private val _state = mutableStateOf(OmissionsState())
    val state: State<OmissionsState> = _state

    init {
        getOmissions()
    }

    private fun getOmissions() {
        getOmissionsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = OmissionsState( omission = it.data )
                }
                is Resource.Error -> {
                    _state.value = OmissionsState (
                        error = it.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = OmissionsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}