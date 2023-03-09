package com.example.bsuirmentors.presentation.mentorList

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.bsuirmentors.common.Resource
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.domain.usecases.GetAllMentorsUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class MentorListViewModel @Inject constructor(
    private val getAllMentorsUseCase: GetAllMentorsUseCase,
): ViewModel() {

    private val _state = mutableStateOf(MentorListState())
    val state: State<MentorListState> = _state

    private val _mentors = MutableStateFlow(emptyList<Mentor>())

//    private val _state = MutableStateFlow(MentorListState())
//    val state: StateFlow<MentorListState> = _state.asStateFlow()

    init {
        getAllMentors()
    }

    private fun getAllMentors() {
        getAllMentorsUseCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _state.value = MentorListState(mentors = it.data ?: emptyList())
                    _mentors.value = state.value.mentors
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


    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val mentorAdapter = moshi.adapter(Mentor::class.java)

//    fun onItemClick(mentor: Mentor) {
//        val mentorJson = mentorAdapter.toJson(mentor)
//    }

//    var isDialogShown by mutableStateOf(false)
//        private set

//    var id by mutableStateOf("")
//        private set

//    fun onItemClick(mentor: Mentor) {
//        isDialogShown = true
//        id = mentor.urlId
//    }
//    fun onDismissDialog() { isDialogShown = false }

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    val mentors = searchText
        .debounce(300L)
        .onEach { _isSearching.update { true } }
        .combine(_mentors) { text, mentors ->
            if (text.isBlank()) {
                mentors
            } else {
                mentors.filter {
//                    delay(2000L)
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