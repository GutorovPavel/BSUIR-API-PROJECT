package com.example.bsuirmentors.presentation.schedule.components

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(title: String, screen: ComposableFun) {}