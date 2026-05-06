package com.example.gublachko.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job

@Composable
fun QuickSwitchIcon(onClick: () -> Job) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .size(65.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.25f),   // сверху почти прозрачный белый
                        Color.White.copy(alpha = 0.05f)    // снизу ещё прозрачнее
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "H",
//            text = app.name.take(1),
            fontSize = 24.sp,
            color = Color.White
        )
    }
}