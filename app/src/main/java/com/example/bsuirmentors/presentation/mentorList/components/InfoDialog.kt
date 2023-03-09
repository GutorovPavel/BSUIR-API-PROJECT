package com.example.bsuirmentors.presentation.mentorList.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.domain.models.Mentor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InfoDialog(
    mentor: Mentor,
    onDismiss:() -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MentorAvatar(
                    painter = rememberAsyncImagePainter(model = mentor.photoLink),
                    size = 150,
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "${mentor.lastName} ${mentor.firstName}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = mentor.middleName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))

                Text(text = "Место работы, должность:", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                mentor.rank?.let {
                    Text(text ="${mentor.rank}",fontSize = 16.sp)
                }
                Row {
                    for (i in mentor.academicDepartment) {
                        if (i == mentor.academicDepartment.last())
                            Text(text = i, fontSize = 16.sp)
                        else
                            Text(text = "$i, ", fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = onDismiss) {
                    Text(text = "Закрыть")
                }
            }
        }
    }
}