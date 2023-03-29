package com.example.bsuirmentors.presentation.components


sealed class RootScreen(
    val route: String
) {
    object PersonalCabinetScreen: RootScreen("personal")
    object ScheduleScreen: RootScreen("schedule")
    object LoginScreen: RootScreen("root")
}

