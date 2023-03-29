package com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.usecases.GetAllGroupsUseCase
import com.example.bsuirmentors.domain.usecases.GetAllMentorsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    private val getAllMentorsUseCase: GetAllMentorsUseCase,
    private val getAllGroupsUseCase: GetAllGroupsUseCase,
): ViewModel() {

    private val _state = mutableStateOf(ScheduleListState())
    val state: State<ScheduleListState> = _state

    private val _mentors = MutableStateFlow(emptyList<Mentor>())
    private val _groups = MutableStateFlow(emptyList<Group>())


//    private val _state = MutableStateFlow(MentorListState())
//    val state: StateFlow<MentorListState> = _state.asStateFlow()

    init {
        getAllMentors()
        getAllGroups()
    }

    private fun getAllMentors() {
        getAllMentorsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = ScheduleListState(mentors = it.data ?: emptyList())
                    _mentors.value = state.value.mentors
                }
                is Resource.Error -> {
                    _state.value = ScheduleListState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = ScheduleListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllGroups() {
        getAllGroupsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = ScheduleListState(groups = it.data ?: emptyList())
                    _groups.value = state.value.groups
                }
                is Resource.Error -> {
                    _state.value = ScheduleListState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = ScheduleListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val groups = searchText
        .debounce(300L)
        .onEach { _isSearching.update { true } }
        .combine(_groups) { text, groups ->
            if (text.isBlank()) {
                groups
            } else {
                groups.filter {
                    it.doesMatchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _groups.value
        )

    val mentors = searchText
        .debounce(300L)
        .onEach { _isSearching.update { true } }
        .combine(_mentors) { text, mentors ->
            if (text.isBlank()) {
                mentors
            } else {
                mentors.filter {
                    it.doesMatchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _mentors.value
        )


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}