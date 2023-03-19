package com.example.bsuirmentors.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppBar(
    title: String = "",
    leftIcon: @Composable () -> Unit,
    actions: @Composable (RowScope.() -> Unit),
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    topBarState: MutableState<Boolean> = mutableStateOf(true)
) {
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = { Text(text = title, textAlign = TextAlign.Center) },
                navigationIcon = {
                    leftIcon()
                },
                backgroundColor = backgroundColor,
                modifier = Modifier.padding(top = 30.dp),
                elevation = 0.dp,
                actions = actions,
                contentColor = contentColor
            )
        }
    )
}
