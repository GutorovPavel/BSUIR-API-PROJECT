package com.example.bsuirmentors.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.DefaultScreen
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg

@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    onClick:() -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

//    val state = viewModel.state.value

    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.padding(20.dp),
                backgroundColor = if (isSystemInDarkTheme()) OnDarkBG else OnLightBg,
                shape = RoundedCornerShape(10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Личный кабинет",
                        modifier = Modifier.padding(vertical = 30.dp),
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary
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
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                usernameText = "05350048"
                                passwordText = "310303250675pg"
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Заполнить")
                        }
                        Button(
                            onClick = {
                                viewModel.login(usernameText, passwordText)
                                onClick()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Войти")
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Вернуться к расписанию",
                modifier = Modifier.clickable {
                    navController.navigate(Graph.SCHEDULE)
                },
                color = Color.White,
                fontStyle = FontStyle.Italic
            )
        }
    }
}