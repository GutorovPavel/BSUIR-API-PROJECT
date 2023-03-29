package com.example.bsuirmentors.presentation.personalCabinet.omissions

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@Composable
fun OmissionsScreen(
    navController: NavController,
    viewModel: OmissionsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val format = SimpleDateFormat("dd.MM.yyyy")
    var oldTerm = 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            state.omission?.let { omissions ->
                LazyColumn() {
                    omissions.reversed().forEach {
                        if(it.term != oldTerm.toString()) {
                            item {
                                Text(
                                    text = "${it.term} cеместр:",
                                    color = MaterialTheme.colors.primary,
                                    style = MaterialTheme.typography.subtitle1,
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                            oldTerm = it.term.toInt()
                        }
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 4.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Text(
                                        text = "Тип документа:",
                                        color = MaterialTheme.colors.primary,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                    Text(text = it.name)

                                    Text(
                                        text = "Дата начала действия:",
                                        color = MaterialTheme.colors.primary,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                    Text(text = format.format(Date(it.dateFrom)))

                                    Text(
                                        text = "Дата окончания действия:",
                                        color = MaterialTheme.colors.primary,
                                        style = MaterialTheme.typography.subtitle1
                                    )
                                    Text(text = format.format(Date(it.dateTo)))

                                    it.note?.let { note ->
                                        Text(
                                            text = "Примечания:",
                                            color = MaterialTheme.colors.primary,
                                            style = MaterialTheme.typography.subtitle1
                                        )
                                        Text(text = note)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}