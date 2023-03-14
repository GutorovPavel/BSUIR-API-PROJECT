package com.example.bsuirmentors.presentation.scheduleLists.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.bsuirmentors.common.Constants
import com.example.bsuirmentors.domain.models.Mentor
import com.example.bsuirmentors.presentation.scheduleLists.components.MentorAvatar

@Composable
fun MentorListItem(
    item: Mentor,
    onItemClick:(Mentor) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.ITEM_HEIGHT.dp)
            .clickable { onItemClick(item) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MentorAvatar(
            painter = rememberAsyncImagePainter(model = item.photoLink),
            size = 50
        )
        Spacer(modifier = Modifier.width(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${item.lastName} ${item.firstName} ${item.middleName}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(7f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Forward",
                modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
            )
        }
    }

}