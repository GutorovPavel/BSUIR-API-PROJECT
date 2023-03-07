package com.example.bsuirmentors.presentation.mentorList.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.CustomSearchBar
import com.example.bsuirmentors.presentation.mentorList.MentorListViewModel
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@SuppressLint("UnrememberedMutableState")
@Composable
fun MentorListScreen(
    viewModel: MentorListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var currentMentor = Mentor(emptyList(), "", "", "", "", "", "", "")
    val items by viewModel.mentors.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomSearchBar(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) OnDarkBG else OnLightBg
            )
        ) {
            if(isSearching || state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(items) { mentor ->
                        MentorListItem(
                            item = mentor,
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