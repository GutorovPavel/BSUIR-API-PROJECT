package com.example.bsuirmentors.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.groupList.components.GroupListScreen
import com.example.bsuirmentors.presentation.mentorDetail.MentorDetailScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Groups.route
    ) {
        composable(route = BottomBarScreen.Groups.route) {
            GroupListScreen()
        }
        composable(route = BottomBarScreen.Mentors.route) {
            MentorListScreen(navController)
        }
        composable(route = DefaultScreen.DetailScreen.route) {
            val currentMentor =
                navController.previousBackStackEntry?.savedStateHandle?.get<Mentor>("mentor")
            currentMentor?.let { it -> MentorDetailScreen(navController, mentor = it) }
        }
    }
}