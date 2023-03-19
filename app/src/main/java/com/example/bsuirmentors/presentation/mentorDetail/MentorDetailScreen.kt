package com.example.bsuirmentors.presentation.mentorDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorAvatar

@Composable
fun MentorDetailScreen(
    navController: NavController,
    mentor: Mentor
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
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = "starButton")
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            )
        },
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MentorAvatar(
                    painter = rememberAsyncImagePainter(model = mentor.photoLink),
                    size = 80,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(
                    text = "${mentor.lastName} ${mentor.firstName} ${mentor.middleName}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Text(text = "Место работы, должность:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                mentor.rank?.let {
                    Text(text ="${mentor.rank}",fontSize = 16.sp)
                }
                for (i in mentor.academicDepartment) Text(text = "- $i", fontSize = 16.sp)
            }
            Divider(Modifier.padding(top = 16.dp))
        }
    }
}