package com.example.bsuirmentors.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bsuirmentors.presentation.groupList.components.GroupListScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Groups: BottomBarScreen("groups", "Группы", Icons.Default.List)
    object Mentors: BottomBarScreen("mentors", "Преподаватели", Icons.Default.Face)
}

sealed class DefaultScreen(
    val route: String
) {
    object DetailScreen: DefaultScreen("detail")
}

