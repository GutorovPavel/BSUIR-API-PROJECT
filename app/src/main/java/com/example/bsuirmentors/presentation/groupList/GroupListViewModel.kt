package com.example.bsuirmentors.presentation.groupList

import com.example.bsuirmentors.presentation.mentorList.MentorListState

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.usecases.GetAllGroupsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class GroupListViewModel @Inject constructor(
    private val getAllGroupsUseCase: GetAllGroupsUseCase
): ViewModel() {

    private val _state = mutableStateOf(GroupListState())
    val state: State<GroupListState> = _state

    private val _groups = MutableStateFlow(emptyList<Group>())

//    private val _state = MutableStateFlow(MentorListState())
//    val state: StateFlow<MentorListState> = _state.asStateFlow()

    init {
        getAllGroups()
    }

    private fun getAllGroups() {
        getAllGroupsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = GroupListState(groups = it.data ?: emptyList())
                    _groups.value = state.value.groups
                }
                is Resource.Error -> {
                    _state.value = GroupListState(error = it.message ?: "An unexpected error occurred...")
                }
                is Resource.Loading -> {
                    _state.value = GroupListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

//    var isDialogShown by mutableStateOf(false)
//        private set
//
//    var id by mutableStateOf("")
//        private set
//
//    fun onItemClick(mentor: Mentor) {
//        isDialogShown = true
//        id = mentor.urlId
//    }
//    fun onDismissDialog() { isDialogShown = false }

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
//                    delay(2000L)
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


    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}