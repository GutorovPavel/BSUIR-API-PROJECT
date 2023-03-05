package com.example.bsuirmentors.presentation.components

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bsuirmentors.presentation.ui.theme.OnBG
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@Composable
fun CustomAppBar(
    title: String = "Title"
) {
    val context = LocalContext.current

    TopAppBar(
        title = { Text(text = title, textAlign = TextAlign.Center) },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Login")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(top = 40.dp),
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                Toast.makeText(context, "Login to use some more features!", Toast.LENGTH_LONG).show()
            }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Authorized menu")
            }
        }
    )
}
