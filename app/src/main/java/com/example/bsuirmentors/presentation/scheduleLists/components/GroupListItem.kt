package com.example.bsuirmentors.presentation.scheduleLists.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.domain.models.Group
import com.example.bsuirmentors.domain.models.Schedule

@Composable
fun GroupListItem(
    item: Group,
    onItemClick:(Group) -> Unit
) {

    val isFavorite by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.ITEM_HEIGHT.dp)
            .clickable { onItemClick(item) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.body1,
                fontSize = 18.sp,
                modifier = Modifier
                    .weight(6f)
                    .align(Alignment.CenterVertically)
            )
            if(isFavorite) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "isFavorite",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Forward",
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }

}