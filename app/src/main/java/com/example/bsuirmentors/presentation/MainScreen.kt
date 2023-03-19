package com.example.bsuirmentors.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bsuirmentors.presentation.components.BottomBarScreen
import com.example.bsuirmentors.presentation.components.BottomNavGraph
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.scheduleLists.ScheduleListScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    BottomNavGraph(navController = navController)
}

//@Composable
//fun BottomBar(navController: NavHostController) {
//
//    val screens = listOf(
//        BottomBarScreen.Groups,
//        BottomBarScreen.Mentors,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    BottomNavigation(
//        backgroundColor = MaterialTheme.colors.background,
//        contentColor = MaterialTheme.colors.onBackground,
//        elevation = 20.dp,
//    ) {
//        screens.forEach { screen ->
//            AddItem(
//                screen = screen,
//                currentDestination = currentDestination,
//                navController = navController
//            )
//        }
//    }
//}
//
//@Composable
//fun RowScope.AddItem(
//    screen: BottomBarScreen,
//    currentDestination: NavDestination?,
//    navController: NavHostController
//) {
//    BottomNavigationItem(
//        label = { Text(text = screen.title) },
//        icon = { Icon(imageVector = screen.icon, contentDescription = "NavIcon") },
//        selected = currentDestination?.hierarchy?.any {
//            it.route == screen.route
//        } == true,
//        onClick = {
//            navController.navigate(screen.route)
//        }
//    )
//}