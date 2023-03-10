package com.example.bsuirmentors.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bsuirmentors.data.remote.dto.Schedules
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.groupList.components.GroupListScreen
import com.example.bsuirmentors.presentation.mentorDetail.MentorDetailScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen
import com.example.bsuirmentors.presentation.schedule.components.ScheduleScreen
import com.example.bsuirmentors.presentation.scheduleLists.ScheduleListScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = DefaultScreen.ScheduleListScreen.route
    ) {
//        composable(route = BottomBarScreen.Groups.route) {
//            GroupListScreen(navController)
//        }
//        composable(route = BottomBarScreen.Mentors.route) {
//            MentorListScreen(navController)
//        }
        composable(route = DefaultScreen.ScheduleListScreen.route) {
            ScheduleListScreen(navController = navController)
        }
        composable(route = DefaultScreen.MentorDetailScreen.route) {
            val currentMentor =
                navController.previousBackStackEntry?.savedStateHandle?.get<Mentor>("mentor")
            currentMentor?.let { it -> MentorDetailScreen(navController, mentor = it) }
        }
        composable(route = DefaultScreen.ScheduleDetailScreen.route + "/{studentGroup}") {
            ScheduleScreen(navController)
        }
//        composable(route = BottomBarScreen.Schedules.route) {
//            ScheduleScreen()
//        }
    }
}