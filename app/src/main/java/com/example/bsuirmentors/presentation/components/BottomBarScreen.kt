package com.example.bsuirmentors.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Groups: BottomBarScreen("groups", "Группы", Icons.Default.List)
    object Mentors: BottomBarScreen("mentors", "Преподаватели", Icons.Default.Face)
    object Schedules: BottomBarScreen("schedules", "Расписания", Icons.Default.DateRange)
}

sealed class DefaultScreen(
    val route: String
) {
    object ScheduleListScreen: DefaultScreen("scheduleList")
    object MentorDetailScreen: DefaultScreen("detail")
    object ScheduleDetailScreen: DefaultScreen("schedule")
    object LoginScreen: DefaultScreen("login")
    object ProfileScreen: DefaultScreen("profile")
}

