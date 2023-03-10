package com.example.bsuirmentors.presentation.scheduleLists.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.DefaultScreen
import com.example.bsuirmentors.presentation.groupList.components.GroupListItem
import com.example.bsuirmentors.presentation.mentorList.components.MentorListItem

@Composable
fun GroupList(
    navController: NavController,
    list: List<Group>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { group ->
            GroupListItem(
                item = group,
                onItemClick = {
                    navController.navigate( route =
                        DefaultScreen.ScheduleDetailScreen.route + "/${it.name}"
                    )
                }
            )
            Divider()
        }
    }
}