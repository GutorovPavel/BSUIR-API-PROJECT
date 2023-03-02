package com.example.bsuirmentors.presentation.mentorList.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.mentorList.MentorListViewModel
import com.example.bsuirmentors.presentation.ui.theme.OnBG
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@SuppressLint("UnrememberedMutableState")
@Composable
fun MentorListScreen(
    viewModel: MentorListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var currentMentor = Mentor(emptyList(), "", "", "", "", "", "", "")

    Column(Modifier.fillMaxSize()) {
        //Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = "Все преподаватели",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 14.dp, end = 14.dp, top = 60.dp)
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .background(
                if (isSystemInDarkTheme()) OnDarkBG else OnBG
            )
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()

            ) {
                items(state.mentors) { mentor ->
                    MentorListItem(
                        mentor = mentor,
                        onItemClick = {
//                            Toast.makeText(context, it.urlId, Toast.LENGTH_SHORT).show()
                            viewModel.onItemClick(it)
                        }
                    )
                    Divider()
                }
            }
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
            }
            if(viewModel.isDialogShown) {
                state.mentors.forEach {
                    if (it.urlId == viewModel.id) {
                        currentMentor = it
                    }
                }
                InfoDialog(
                    mentor = currentMentor,
                    onDismiss = { viewModel.onDismissDialog() }
                )
            }
        }
    }
}