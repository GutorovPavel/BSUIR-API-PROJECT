package com.example.bsuirmentors.presentation.mentorDetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.BottomBarScreen
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.mentorList.components.MentorAvatar

@Composable
fun MentorDetailScreen(
    navController: NavController,
    mentor: Mentor
) {
    Scaffold(
        topBar = {
            CustomAppBar(
                title = "",
                leftIcon = Icons.Default.ArrowBack,
                onLeftIconClick = { navController.popBackStack() },
                rightIcon = Icons.Default.Star,
                onRightIconClick = {}
            )
        }
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
            Divider(Modifier.padding(top = 12.dp))
        }
    }
}