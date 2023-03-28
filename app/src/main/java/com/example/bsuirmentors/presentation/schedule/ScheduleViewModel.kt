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

    private val _scheduleState = mutableStateOf(ScheduleState())
    val scheduleState: State<ScheduleState> = _scheduleState

    private val _currentWeekState = mutableStateOf(CurrentWeekState())
    val currentWeekState: State<CurrentWeekState> = _currentWeekState

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
                    _currentWeekState.value = CurrentWeekState(currentWeek = it.data)
                }
                is Resource.Error -> {
                    _currentWeekState.value = CurrentWeekState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _currentWeekState.value = CurrentWeekState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getSchedule(studentGroup: String) {
        getScheduleByGroupUseCase(studentGroup).onEach {
            when(it) {
                is Resource.Success -> {
                    _scheduleState.value = ScheduleState(schedule = it.data)
                }
                is Resource.Error -> {
                    _scheduleState.value = ScheduleState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _scheduleState.value = ScheduleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}