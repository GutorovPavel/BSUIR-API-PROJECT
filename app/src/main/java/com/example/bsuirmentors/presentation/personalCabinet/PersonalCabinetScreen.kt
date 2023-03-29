package com.example.bsuirmentors.presentation.personalCabinet

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.components.drawer.DrawerBody
import com.example.bsuirmentors.presentation.components.drawer.DrawerHeader
import com.example.bsuirmentors.presentation.components.drawer.DrawerItem
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalNavGraph
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalScreen
import com.example.bsuirmentors.presentation.personalCabinet.profile.PersonalCvViewModel
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.DefaultScreen
import kotlinx.coroutines.launch

@Composable
fun PersonalCabinetScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: PersonalCabinetViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val screens = listOf(
        PersonalScreen.ProfileScreen,
        PersonalScreen.GradeBookScreen,
        PersonalScreen.OmissionsScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val topBarDestination = screens.any { it.route == currentDestination?.route }

    Scaffold(
        topBar = {
            if(topBarDestination) {
                CustomAppBar(
                    leftIcon = {
                        IconButton(onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "MenuButton"
                            )
                        }
                    },
                    actions = {
                        Button(
                            onClick = {
                                viewModel.logout()
                                navController.popBackStack()
                                navController.navigate(Graph.LOGIN)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.background,
                                contentColor = MaterialTheme.colors.onBackground
                            ),
                            elevation = ButtonDefaults.elevation(0.dp)
                        ) {
                            Text(text = "Выйти")
                        }
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                                navController.navigate(Graph.SCHEDULE)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "toSchedules"
                            )
                        }
                    },
                    contentColor = MaterialTheme.colors.onBackground,
                    backgroundColor = MaterialTheme.colors.background
                )
            }
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    DrawerItem(
                        id = "profile",
                        name = "Профиль",
                        icon = Icons.Default.Person
                    ),
                    DrawerItem(
                        id = "scorecard",
                        name = "Зачетка",
                        icon = Icons.Default.ImportContacts
                    ),
                    DrawerItem(
                        id = "study",
                        name = "Учеба",
                        icon = Icons.Default.School
                    ),
                    DrawerItem(
                        id = "rating",
                        name = "Рейтинг",
                        icon = Icons.Default.HotelClass
                    ),
                    DrawerItem(
                        id = "skips",
                        name = "Пропуски",
                        icon = Icons.Default.Rule
                    ),
                ),
                onItemClick = {
                    when(it.name) {
                        "Рейтинг" -> {
                            navController.navigate(PersonalScreen.GradeBookScreen.route)
                            scope.launch { scaffoldState.drawerState.close() }
                        }
                        "Пропуски" -> {
                            navController.navigate(PersonalScreen.OmissionsScreen.route)
                            scope.launch { scaffoldState.drawerState.close() }
                        }
                    }
                }
            )
        },
        scaffoldState = scaffoldState
    ) {
        PersonalNavGraph(navController = navController)
    }
}