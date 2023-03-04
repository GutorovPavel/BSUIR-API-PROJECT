package com.example.bsuirmentors.presentation.mentorList.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.mentorList.MentorListViewModel
import com.example.bsuirmentors.presentation.ui.theme.OnBG
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MentorListScreen(
    viewModel: MentorListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var currentMentor = Mentor(emptyList(), "", "", "", "", "", "", "")
    val mentors by viewModel.mentors.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

//    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp)
    ) {
        //Spacer(modifier = Modifier.height(28.dp))
        CustomSearchBar(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 14.dp)
            .background(
                if (isSystemInDarkTheme()) OnDarkBG else OnBG
            )
        ) {
            if(isSearching || state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(mentors) { mentor ->
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
//            if(state.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//            }
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