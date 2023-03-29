package com.example.bsuirmentors.presentation.personalCabinet.navigation


sealed class PersonalScreen (
    val route: String
) {
    object ProfileScreen: PersonalScreen("profile")
    object GradeBookScreen: PersonalScreen("gradeBook")
    object OmissionsScreen: PersonalScreen("omissions")
}
