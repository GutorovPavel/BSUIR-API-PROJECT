package com.example.bsuirmentors.presentation.scheduleLists.components

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit
data class TabItem(
    val title: String,
    val screen: ComposableFun
)
