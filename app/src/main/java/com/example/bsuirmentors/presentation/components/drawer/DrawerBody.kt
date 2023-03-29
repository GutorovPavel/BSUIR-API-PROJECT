package com.example.bsuirmentors.presentation.components.drawer

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<DrawerItem>,
    onItemClick:(DrawerItem) -> Unit
) {
    LazyColumn() {
        items(items) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(it) }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = it.icon, contentDescription = "item description")
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = it.name,
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
            }
        }
    }
}