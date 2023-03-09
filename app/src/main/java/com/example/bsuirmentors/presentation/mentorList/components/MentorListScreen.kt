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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bsuirmentors.common.extensions.navigate
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.CustomSearchBar
import com.example.bsuirmentors.presentation.components.DefaultScreen
import com.example.bsuirmentors.presentation.mentorList.MentorListState
import com.example.bsuirmentors.presentation.mentorList.MentorListViewModel
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@SuppressLint("UnrememberedMutableState")
@Composable
fun MentorListScreen(
    navController: NavController,
    viewModel: MentorListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val items by viewModel.mentors.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Все преподаватели",
                leftIcon = Icons.Default.AccountCircle,
                onLeftIconClick = {},
                rightIcon = Icons.Default.Menu,
                onRightIconClick = {},
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomSearchBar(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

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
                                    navController.currentBackStackEntry?.savedStateHandle?.set(
                                        key = "mentor",
                                        value = mentor
                                    )
                                    navController.navigate(DefaultScreen.DetailScreen.route)
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
//            if(viewModel.isDialogShown) {
//                state.mentors.forEach {
//                    if (it.urlId == viewModel.id) {
//                        currentMentor = it
//                    }
//                }
//                InfoDialog(
//                    mentor = currentMentor,
//                    onDismiss = { viewModel.onDismissDialog() }
//                )
//            }
            }

        }
    }

}