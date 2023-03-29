package com.example.bsuirmentors.presentation.scheduleCabinet.schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components.MentorAvatar
import com.example.bsuirmentors.presentation.scheduleCabinet.schedule.ScheduleViewModel
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg

@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val state = viewModel.scheduleState.value
    val week = listOf(
        state.schedule?.schedules?.понедельник?: emptyList(),
        state.schedule?.schedules?.вторник?: emptyList(),
        state.schedule?.schedules?.среда?: emptyList(),
        state.schedule?.schedules?.четверг?: emptyList(),
        state.schedule?.schedules?.пятница?: emptyList(),
        state.schedule?.schedules?.суббота?: emptyList()
    )
    val monday = state.schedule?.schedules?.понедельник?: emptyList()
    val currentWeek = viewModel.currentWeekState.value.currentWeek

    Scaffold(
        topBar = {
            CustomAppBar(
                leftIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "backButton")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "starButton")
                    }
                },
                title = state.schedule?.studentGroupDto?.name ?: "",
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if(state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn {
                    item { Text(text = "Неделя: $currentWeek")}
                    week.forEach { day ->
                        item { Text(text = "") }
                        items(day) {
                            if(
                                it.weekNumber.contains(currentWeek) &&
                                it.lessonTypeAbbrev != "Экзамен" &&
                                it.lessonTypeAbbrev != "Консультация"
                            ) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp, vertical = 4.dp),
                                    backgroundColor = if (isSystemInDarkTheme()) OnDarkBG else OnLightBg,
                                    shape = RoundedCornerShape(8.dp)

                                ) {

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row {
                                            Column(horizontalAlignment = Alignment.End) {
                                                Text(
                                                    text = it.startLessonTime,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 20.sp
                                                )
                                                Text(text = it.endLessonTime)
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .padding(horizontal = 8.dp)
                                                    .background(
                                                        when (it.lessonTypeAbbrev) {
                                                            "ЛК" -> Color.Green
                                                            "ПЗ" -> Color.Red
                                                            "ЛР" -> Color.Yellow
                                                            else -> {
                                                                Color.Gray
                                                            }
                                                        }
                                                    )
                                                    .width(6.dp)
                                                    .height(54.dp)
                                            )
                                            Column {
                                                Text(
                                                    text = it.subject + " " + it.numSubgroup.toString(),
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 20.sp
                                                )
                                                it.auditories.forEach { auditorium ->
                                                    Text(text = auditorium)
                                                }
                                            }
                                        }
                                        it.employees.forEach { employee ->
                                            MentorAvatar(
                                                painter = rememberAsyncImagePainter(model = employee.photoLink),
                                                size = 50
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
