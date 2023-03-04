package com.example.bsuirmentors.presentation.mentorList.components

import android.util.Log
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsuirmentors.presentation.ui.theme.OnBG
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG

@Composable
fun CustomSearchBar(
    value: String,
    onValueChange:(String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    val textAnim = animateToZero(value = 40, flag = isFocused)
    val paddingAnim = animateToZero(value = 30, flag = isFocused)
    Text(
        text = "Все преподаватели",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(top = (30 + paddingAnim).dp)
            .height(textAnim.dp)
    )
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp)
            .background(
                if (isSystemInDarkTheme()) OnDarkBG else OnBG
            )
            .onFocusChanged {
                isFocused = !isFocused
            },
        maxLines = 1,
        label = { Text(text = "Найти преподавателя") },
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

@Composable
fun animateToZero(value: Int, flag: Boolean): Int {
    val textAnim by animateIntAsState(
        targetValue = if(flag) value else 0,
        animationSpec = tween(
            durationMillis = 100,
            easing = FastOutSlowInEasing
        )
    )
    return textAnim
}
