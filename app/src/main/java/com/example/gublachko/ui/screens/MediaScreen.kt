package com.example.gublachko.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gublachko.utils.launchApp

@Composable
fun MediaScreen() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Карточка навигации (70% ширины)
        Card(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
                .clickable {
                    // Здесь можно запустить Яндекс.Навигатор
                    launchApp(context, "ru.yandex.yandexnavi")
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f) // прозрачно-белый фон
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Навигация",
                    fontSize = 24.sp
                )
            }
        }

        // Карточка музыки (30% ширины)
        Card(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
                .clickable {
                    // Запуск Яндекс.Музыки
                    launchApp(context, "ru.yandex.music")
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.3f) // прозрачно-белый фон
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Музыка",
                    fontSize = 24.sp
                )
            }
        }
    }
}