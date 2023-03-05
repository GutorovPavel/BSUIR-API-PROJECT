package com.example.bsuirmentors.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen
import com.example.bsuirmentors.presentation.searchScreen.SearchScreen
import com.example.bsuirmentors.presentation.ui.theme.BSUIRMentorsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)
        setContent {
            BSUIRMentorsTheme {
                // A surface container using the 'background' color from the theme
                val systemUiController = rememberSystemUiController()

                SideEffect {
                    systemUiController.setStatusBarColor(color = Color.Transparent)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { CustomAppBar("IIS Mobile") }
                    ) {
                        SearchScreen()
                    }
                }
            }
        }
    }
}


