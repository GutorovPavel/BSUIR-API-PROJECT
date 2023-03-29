package com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bsuirmentors.data.util.SessionManager
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.CustomSearchBar
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.DefaultScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components.GroupList
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components.MentorList
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components.TabItem
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ScheduleListScreen(
    navController: NavController,
    onClick:() -> Unit,
    viewModel: ScheduleListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val mentors by viewModel.mentors.collectAsState()
    val groups by viewModel.groups.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    val lazyListState1 = rememberLazyListState()
    val lazyListState2 = rememberLazyListState()

    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    //SCOPE
    val scope = rememberCoroutineScope()

    // TABS
    val groupTab = TabItem("Группы") {
        GroupList(navController = navController, list = groups, lazyListState = lazyListState1)
    }
    val mentorTab = TabItem("Преподаватели") {
        MentorList(navController = navController, list = mentors, lazyListState = lazyListState2)
    }

    val tabs = listOf(
        groupTab,
        mentorTab,
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    val searchBarState = remember { mutableStateOf(true) }

    if (pagerState.currentPage == 0)
        searchBarState.value = !lazyListState1.isScrolled
    else
        searchBarState.value = !lazyListState2.isScrolled

    //BOTTOM SHEET
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()) {
                Text(text = "Bottom Sheet View", modifier = Modifier.align(Alignment.Center))
            }
        },
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
        ) {
            CustomAppBar(
                leftIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "menuButton")
                    }
                },
                actions = {
                    AnimatedVisibility(visible = !searchBarState.value) {
                        IconButton(onClick = {
                            scope.launch {
                                searchBarState.value = true
                            }
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "searchButton")
                        }
                    }
                    IconButton(onClick = { onClick() }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "ProfileButton"
                        )
                    }
                },
                title = "IIS BSUIR",
                contentColor = Color.White
            )
            AnimatedVisibility(
                visible = searchBarState.value,
                content = {
                    CustomSearchBar(
                        value = searchText,
                        onValueChange = viewModel::onSearchTextChange,
                    )
                }
            )

            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        if (isSystemInDarkTheme()) OnDarkBG else OnLightBg
                    )
            ) {
                TabRow(
//                    modifier = Modifier.clip(
//                        RoundedCornerShape(
//                            bottomStart = 10.dp,
//                            bottomEnd = 10.dp
//                        )
//                    ),
                    selectedTabIndex = pagerState.currentPage,
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            if (isSystemInDarkTheme()) OnDarkBG else OnLightBg
                        )
                ) {
                    if (isSearching || state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    } else {  //tabs
                        HorizontalPager(state = pagerState) { page ->
                            tabs[page].screen()
                        }
                    }
                    if (state.error.isNotBlank()) {
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
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 1