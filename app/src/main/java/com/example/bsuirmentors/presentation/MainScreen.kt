package com.example.bsuirmentors.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.bsuirmentors.presentation.components.RootNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    RootNavGraph(navController = navController)
}