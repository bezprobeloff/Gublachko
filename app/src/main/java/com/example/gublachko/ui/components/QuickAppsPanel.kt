package com.example.gublachko.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gublachko.data.models.QuickApp
import com.example.gublachko.utils.launchApp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Job

@Composable
fun QuickAppsPanel(onSwitchPage: () -> Job) {
    val context = LocalContext.current
    val quickApps = listOf(
        QuickApp("Музыка", "ru.yandex.music"),
        QuickApp("Карты", "ru.yandex.yandexnavi"),
        QuickApp("Настройки", "com.android.settings"),
        QuickApp("Настройки", "com.android.settings")
    )

    Column(
        modifier = Modifier
            .width(90.dp)
            .fillMaxHeight()
            .background(Color.White.copy(alpha = 0.3f)), // прозрачно-белый фон
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top)
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        quickApps.forEach { quick ->
            QuickAppIcon(quick) {
                launchApp(context, quick.packageName)
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        QuickSwitchIcon(onSwitchPage)

        Spacer(modifier = Modifier.weight(1f))
    }
}