package com.example.bsuirmentors.presentation.searchScreen

import androidx.lifecycle.ViewModel
import com.example.bsuirmentors.domain.usecases.GetAllMentorsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ScreenViewModel @Inject constructor(

): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}
