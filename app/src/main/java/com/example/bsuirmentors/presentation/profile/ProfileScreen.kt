package com.example.bsuirmentors.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorAvatar
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: PersonalCvViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val user = state.personalCv

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
            )
        },
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isSystemInDarkTheme()) OnDarkBG else OnLightBg
                )
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    MentorAvatar(
                        painter = rememberAsyncImagePainter(model = user?.photoUrl),
                        size = 80,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                    Text(
                        text = user?.lastName ?: "",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Логин: ${user?.officeEmail}", fontSize = 16.sp)
                        Text(text = "Пароль: ${user?.officePassword}", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}