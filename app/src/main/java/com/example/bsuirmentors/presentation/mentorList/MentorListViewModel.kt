package com.example.bsuirmentors.presentation.mentorList

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.usecases.GetAllMentorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MentorListViewModel @Inject constructor(
    private val getAllMentorsUseCase: GetAllMentorsUseCase
): ViewModel() {

    private val _state = mutableStateOf(MentorListState())
    val state: State<MentorListState> = _state

    init {
        getAllMentors()
    }

    private fun getAllMentors() {
        getAllMentorsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = MentorListState(mentors = it.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = MentorListState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = MentorListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    var isDialogShown by mutableStateOf(false)
        private set

    var id by mutableStateOf("")
        private set

    fun onItemClick(mentor: Mentor) {
        isDialogShown = true
        id = mentor.urlId
    }
    fun onDismissDialog() { isDialogShown = false }
}