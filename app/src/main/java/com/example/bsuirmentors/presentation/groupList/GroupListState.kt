package com.example.bsuirmentors.presentation.groupList

import com.example.bsuirmentors.domain.models.Group

data class GroupListState(
    val isLoading: Boolean = false,
    val groups: List<Group> = emptyList(),
    val error: String = ""
)