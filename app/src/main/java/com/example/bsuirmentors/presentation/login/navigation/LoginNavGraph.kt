package com.example.bsuirmentors.presentation.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.login.LoginScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.scheduleNavGraph

fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.LOGIN,
        startDestination = RootScreen.LoginScreen.route
    ) {
        composable(
            route = RootScreen.LoginScreen.route
        ) {
            LoginScreen(navController, onClick = {
                navController.navigate(Graph.PROFILE)
            })
        }
        scheduleNavGraph(navController)
    }
}