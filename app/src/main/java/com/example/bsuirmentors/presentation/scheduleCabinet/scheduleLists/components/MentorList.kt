package com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.DefaultScreen

@Composable
fun MentorList(
    navController: NavController,
    lazyListState: LazyListState,
    list: List<Mentor>
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { item ->
            MentorListItem(
                item = item,
                onItemClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "mentor",
                        value = it
                    )
                    navController.navigate(DefaultScreen.MentorDetailScreen.route)
                }
            )
            Divider()
        }
    }
}