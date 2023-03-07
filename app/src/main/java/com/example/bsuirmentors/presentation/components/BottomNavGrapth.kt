package com.example.bsuirmentors.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bsuirmentors.presentation.groupList.components.GroupListScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen

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
            MentorListScreen()
        }
    }
}