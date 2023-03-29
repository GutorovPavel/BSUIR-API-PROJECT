package com.example.bsuirmentors.presentation.personalCabinet.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.data.remote.dto.login.AuthUserDto
import com.example.bsuirmentors.presentation.components.CustomAppBar
import com.example.bsuirmentors.presentation.components.Graph
import com.example.bsuirmentors.presentation.components.RootScreen
import com.example.bsuirmentors.presentation.components.drawer.DrawerBody
import com.example.bsuirmentors.presentation.components.drawer.DrawerHeader
import com.example.bsuirmentors.presentation.components.drawer.DrawerItem
import com.example.bsuirmentors.presentation.personalCabinet.navigation.PersonalScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.navigation.DefaultScreen
import com.example.bsuirmentors.presentation.scheduleCabinet.scheduleLists.components.MentorAvatar
import com.example.bsuirmentors.presentation.ui.theme.OnDarkBG
import com.example.bsuirmentors.presentation.ui.theme.OnLightBg
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    onClick:() -> Unit,
    viewModel: PersonalCvViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(state.error) {
        if(state.error == "no cookie")
            navController.navigate(Graph.LOGIN)
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        state.personalCv?.let {
            LazyColumn() {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        MentorAvatar(
                            painter = rememberAsyncImagePainter(model = it.photoUrl),
                            size = 150,
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                        Text(
                            text = "${it.lastName} ${it.firstName} ${it.middleName}",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(text = "Студент ${it.course} курса")
                            Text(text = "Факультет ${it.faculty}, ${it.speciality}")
                            Text(text = it.birthDate)
                            Text(text = "Рейтинг: ${it.rating}")
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Ключевые навыки:", fontSize = 16.sp)
                            it.skills?.forEach {
                                Text(text = it.name, fontSize = 16.sp)
                            }
                            Text(text = "Ссылки:", fontSize = 16.sp)
                            it.references?.forEach {
                                Text(text = it.name, fontSize = 16.sp)
                            }
                        }
                    }
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = "Microsoft Office 365:", fontSize = 16.sp)
                            Text(text = "Логин: ${it.officeEmail}", fontSize = 16.sp)
                            Text(text = "Пароль: ${it.officePassword}", fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }

}