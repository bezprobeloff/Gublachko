package com.example.gublachko.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.ui.platform.LocalLocale

@Composable
fun StatusBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp) // Высота кастомной панели
            .padding(horizontal = 16.dp)
    ) {
        // Псевдоним для SIM-карты и оператора связи
        Text(
            text = "MegaPhone",
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        // Отображение текущего времени
        Text(
            // изначально было так
//            text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()),
            text = SimpleDateFormat("HH:mm", LocalLocale.current.platformLocale).format(Date()),
            fontSize = 16.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Иконка уровня заряда батареи (заглушка)
        Text(
            text = "🔋 100%",
            fontSize = 14.sp,
            color = Color.White
        )
    }
}