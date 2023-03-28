package com.example.bsuirmentors.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.presentation.components.BottomBarScreen
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.DefaultScreen
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorAvatar
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: PersonalCvViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CustomAppBar(
                leftIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "MenuButton")
                    }
                },
                actions = {
                    Button(
                        onClick = {
                            viewModel.logout()
                            navController.navigate(DefaultScreen.LoginScreen.route) {
                                popUpTo(DefaultScreen.ProfileScreen.route) {
                                    inclusive = true
                                }
                            }
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
                            navController.navigate(DefaultScreen.ScheduleListScreen.route) {
                                popUpTo(DefaultScreen.ProfileScreen.route) {
                                    inclusive = true
                                }
                            } 
                        }
                    ) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "toSchedules")
                    }
                },
                contentColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background
            )
        },
        drawerContent = {
            Text(text = "Профиль")
            Text(text = "Зачетка")
            Text(text = "Учеба")
            Text(text = "Рейтинг")
            Text(text = "Пропуски")
        },
        scaffoldState = scaffoldState
    ) {

        LaunchedEffect(state.error) {
            if(state.error == "no cookie")
                navController.navigate(DefaultScreen.LoginScreen.route) {
                    popUpTo(DefaultScreen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)) {

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.personalCv?.let {
                LazyColumn() {
                    item {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            MentorAvatar(
                                painter = rememberAsyncImagePainter(model = it.photoUrl),
                                size = 150,
                                modifier = Modifier.padding(vertical = 20.dp)
                            )
                            Text(
                                text = "${it.lastName} ${it.firstName} ${it.middleName}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colors.primary
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(text = "Студент ${it.course} курса")
                                Text(text = "Факультет ${it.faculty}, ${it.speciality}")
                                Text(text = it.birthDate)
                                Text(text = "Рейтинг: ${it.rating}")
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = "Ключевые навыки:", fontSize = 16.sp)
                                it.skills?.forEach {
                                    Text(text = it.name, fontSize = 16.sp)
                                }
                                Text(text = "Ссылки:", fontSize = 16.sp)
                                it.references?.forEach {
                                    Text(text = it.name, fontSize = 16.sp)
                                }
                            }
                        }
                    }
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = "Microsoft Office 365:", fontSize = 16.sp)
                                Text(text = "Логин: ${it.officeEmail}", fontSize = 16.sp)
                                Text(text = "Пароль: ${it.officePassword}", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        }

    }
}