package com.example.bsuirmentors.presentation.scheduleCabinet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.mentorDetail.MentorDetailScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.schedule.components.ScheduleScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.ScheduleListScreen

fun NavGraphBuilder.scheduleNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.SCHEDULE,
        startDestination = DefaultScreen.ScheduleListScreen.route
    ) {
        composable(route = DefaultScreen.ScheduleListScreen.route) {
            ScheduleListScreen(navController = navController, onClick = {
                navController.navigate(Graph.PROFILE)
            })
        }
        composable(route = DefaultScreen.MentorDetailScreen.route) {
            val currentMentor =
                navController.previousBackStackEntry?.savedStateHandle?.get<Mentor>("mentor")
            currentMentor?.let { it -> MentorDetailScreen(navController, mentor = it) }
        }
        composable(route = DefaultScreen.ScheduleDetailScreen.route + "/{studentGroup}") {
            ScheduleScreen(navController)
        }
    }
}