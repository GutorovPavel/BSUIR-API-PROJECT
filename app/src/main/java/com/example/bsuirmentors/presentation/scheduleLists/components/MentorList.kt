package com.example.bsuirmentors.presentation.scheduleLists.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.DefaultScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListItem

@Composable
fun MentorList(
    navController: NavController,
    list: List<Mentor>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
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