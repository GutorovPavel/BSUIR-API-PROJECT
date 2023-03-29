package com.example.bsuirmentors.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bsuirmentors.presentation.login.LoginScreen
import com.example.bsuirmentors.presentation.login.navigation.loginNavGraph
import com.example.bsuirmentors.presentation.personalCabinet.PersonalCabinetScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.scheduleNavGraph

@Composable
fun RootNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.PROFILE
    ) {
        scheduleNavGraph(navController = navController)
//        composable(route = RootScreen.LoginScreen.route) {
//            BackHandler(true) {}
//            LoginScreen()
//        }
        loginNavGraph(navController = navController)
        composable(route = Graph.PROFILE) {
            PersonalCabinetScreen()
        }
//        personalNavGraph(navController = navController)
//        composable(route = Graph.SCHEDULE) {
//            ScheduleCabinetScreen()
//        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val SCHEDULE = "schedule_graph"
    const val PROFILE = "profile_graph"
    const val LOGIN = "login"
}