package com.example.bsuirmentors.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bsuirmentors.data.util.SessionManager
import com.example.bsuirmentors.presentation.components.DefaultScreen
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(if (isSystemInDarkTheme()) OnDarkBG else OnLightBg),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Личный кабинет",
                modifier = Modifier.padding(vertical = 30.dp),
                style = MaterialTheme.typography.h6
            )
            TextField(
                value = usernameText,
                onValueChange = {
                    usernameText = it
                },
                modifier = Modifier.padding(top = 10.dp)
            )
            TextField(
                value = passwordText,
                onValueChange = {
                    passwordText = it
                },
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    usernameText = "05350048"
                    passwordText = "310303250675pg"
                }) {
                    Text(text = "Заполнить")
                }
                Button(
                    onClick = {
                        viewModel.saveAuthToken(usernameText, passwordText)
//                        state.user?.let {
//                            navController.currentBackStackEntry?.savedStateHandle?.set(
//                                key = "user",
//                                value = it
//                            )
//                        }
//                        navController.navigate(DefaultScreen.MentorDetailScreen.route)
                    },
                ) {
                    Text(text = "Войти")
                }
            }
            Text(text = state.user.toString())            
        }

    }
}