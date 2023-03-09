package com.example.bsuirmentors.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppBar(
    title: String = "Title",
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    onLeftIconClick:() -> Unit,
    onRightIconClick:() -> Unit
) {

    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            IconButton(onClick = onLeftIconClick) {
                leftIcon?.let { Icon(imageVector = it, contentDescription = "Login") }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(top = 30.dp),
        elevation = 0.dp,
        actions = {
            IconButton(onClick = onRightIconClick) {
                rightIcon?.let { Icon(imageVector = it, contentDescription = "Authorized menu") }
            }
        }
    )
}
