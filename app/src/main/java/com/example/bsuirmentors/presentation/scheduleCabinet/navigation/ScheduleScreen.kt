package com.example.bsuirmentors.presentation.scheduleCabinet.navigation

sealed class DefaultScreen(
    val route: String
) {
    object ScheduleListScreen: DefaultScreen("scheduleList")
    object MentorDetailScreen: DefaultScreen("detail")
    object ScheduleDetailScreen: DefaultScreen("scheduleDetail")
}
