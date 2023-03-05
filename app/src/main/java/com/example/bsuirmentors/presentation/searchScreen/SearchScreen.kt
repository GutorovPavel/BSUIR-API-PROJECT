package com.example.bsuirmentors.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bsuirmentors.presentation.components.CustomSearchBar
import com.example.bsuirmentors.presentation.components.TabItem
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SearchScreen(
    viewModel: ScreenViewModel = hiltViewModel()
) {

    val searchText by viewModel.searchText.collectAsState()

    val tabs = listOf(
        TabItem.Groups,
        TabItem.Mentors
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    Column(Modifier.fillMaxSize()) {
        CustomSearchBar(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
        )
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()
    
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, it)
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                      pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text( text = tabItem.title ) },
                icon = {}
            )
        }
        
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}

