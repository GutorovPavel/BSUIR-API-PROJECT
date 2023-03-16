package com.example.bsuirmentors.presentation.profile

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
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorAvatar

@Composable
fun ProfileScreen(
    navController: NavController,
    user: AuthUserDto
) {
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
                rightIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "starButton")
                    }
                },
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MentorAvatar(
                painter = rememberAsyncImagePainter(model = user.photoUrl),
                size = 80,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Text(
                text = user.fio,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Column {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Группа: ${user.group}", fontSize = 16.sp)
                Text(text = "Email: ${user.email}", fontSize = 16.sp)
                Text(text = "Номер телефона: ${user.phone}", fontSize = 16.sp)
            }
        }
    }
}