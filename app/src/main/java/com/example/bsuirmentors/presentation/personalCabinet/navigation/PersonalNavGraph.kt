package com.example.bsuirmentors.presentation.personalCabinet.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.login.navigation.loginNavGraph
import com.example.bsuirmentors.presentation.personalCabinet.gradeBook.GradeBookScreen
import com.example.bsuirmentors.presentation.personalCabinet.omissions.OmissionsScreen
import com.example.bsuirmentors.presentation.personalCabinet.profile.ProfileScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.scheduleNavGraph


@Composable
fun PersonalNavGraph (
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.PROFILE,
        startDestination = PersonalScreen.ProfileScreen.route
    ) {
        composable(route = PersonalScreen.ProfileScreen.route) {
            BackHandler(true) {}
            ProfileScreen(navController, onClick = {
                navController.navigate(Graph.SCHEDULE)
            })
        }
        composable(route = PersonalScreen.OmissionsScreen.route) {
            OmissionsScreen(navController)
        }
        composable(route = PersonalScreen.GradeBookScreen.route) {
            GradeBookScreen(navController)
        }
        scheduleNavGraph(navController)
        loginNavGraph(navController)
    }
}
