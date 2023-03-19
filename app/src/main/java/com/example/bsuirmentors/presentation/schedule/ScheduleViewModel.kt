package com.example.bsuirmentors.presentation.schedule

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.usecases.GetCurrentWeekUseCase
import com.example.bsuirmentors.domain.usecases.GetScheduleByGroupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleByGroupUseCase: GetScheduleByGroupUseCase,
    private val getCurrentWeekUseCase: GetCurrentWeekUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(ScheduleState())
    val state: State<ScheduleState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_STUDENT_GROUP)?.let{
            getSchedule(it)
        }
        getCurrentWeek()
    }

    private fun getCurrentWeek() {
        getCurrentWeekUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = ScheduleState(currentWeek = it.data)
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSchedule(studentGroup: String) {
        getScheduleByGroupUseCase(studentGroup).onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = ScheduleState(schedule = it.data)
                }
                is Resource.Error -> {
                    _state.value = ScheduleState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}