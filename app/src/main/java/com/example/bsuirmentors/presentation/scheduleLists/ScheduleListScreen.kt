package com.example.bsuirmentors.presentation.scheduleLists

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.CustomSearchBar
import com.example.bsuirmentors.presentation.scheduleLists.components.GroupList
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorList
import com.example.bsuirmentors.presentation.scheduleLists.components.TabItem
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ScheduleListScreen(
    navController: NavController,
    viewModel: ScheduleListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val mentors by viewModel.mentors.collectAsState()
    val groups by viewModel.groups.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()


    val groupTab = TabItem("Группы") {
        GroupList(navController = navController, list = groups)
    }
    val mentorTab = TabItem("Преподаватели") {
        MentorList(navController = navController, list = mentors)
    }

    val tabs = listOf(
        groupTab,
        mentorTab
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)
    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomSearchBar(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
        )
        Spacer(modifier = Modifier.height(16.dp))
//        Divider()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) OnDarkBG else OnLightBg
            )
        ) {
            if(isSearching || state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {  //tabs

                Column(Modifier.fillMaxSize()) {
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.onBackground,
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                            )
                        }
                    ) {
                        tabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = pagerState.currentPage == index,
                                onClick = {
                                    scope.launch {
                                        pagerState.animateScrollToPage(index)
                                    }
                                },
                                text = { Text(text = tab.title) }
                            )
                        }
                    }
                    HorizontalPager(state = pagerState) { page ->
                        tabs[page].screen()
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
        }

    }


}