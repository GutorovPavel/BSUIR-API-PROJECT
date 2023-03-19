package com.example.bsuirmentors.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import com.example.bsuirmentors.presentation.ui.theme.Shapes

@Composable
fun CustomSearchBar(
    value: String,
    onValueChange:(String) -> Unit,
    onClose:() -> Unit = {},
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
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = MaterialTheme.colors.background
        ),
        trailingIcon = {
            if (isFocused) {
                IconButton(
                    onClick = {
                        focusManager.clearFocus()
                        onValueChange("")
                        onClose()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "hideKeyboard")
                }
            }
        }
    )
}

