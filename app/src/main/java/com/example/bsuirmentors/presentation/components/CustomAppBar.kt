package com.example.bsuirmentors.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppBar(
    title: String = "",
    leftIcon: @Composable () -> Unit,
    rightIcon: @Composable () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            leftIcon()
        },
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(top = 30.dp),
        elevation = 0.dp,
        actions = {
            rightIcon()
        }
    )
}
