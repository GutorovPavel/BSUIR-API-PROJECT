package com.example.bsuirmentors.presentation.mentorList.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun MentorAvatar(
    painter: Painter,
    size: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = "Mentor Avatar",
        modifier = modifier
            .clip(shape = RoundedCornerShape(100))
            .size(size.dp)
    )
}