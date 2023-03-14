package com.example.bsuirmentors.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchBar(
    value: String,
    onValueChange:(String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(true) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .onFocusChanged {
                isFocused = !isFocused
            },
        maxLines = 1,
        label = { Text(text = "Найти...") },
        shape = RoundedCornerShape(70),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    focusManager.clearFocus()
                    onValueChange("")
                }
            ) {
                Icon(imageVector = Icons.Filled.Close, contentDescription = "hideKeyboard")
            }
        }
    )
}

