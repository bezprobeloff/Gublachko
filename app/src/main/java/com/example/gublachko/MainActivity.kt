package com.example.gublachko

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import com.example.gublachko.ui.screens.MainScreen
import com.example.gublachko.ui.theme.GublachkoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // убирает встроенный статус бар
        enableEdgeToEdge()

        // Устанавливаем флаги для скрытия навигационной панели
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            GublachkoTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Фоновое изображение
                    Image(
                        painter = painterResource(id = R.drawable.ios_wallpaper), // файл должен лежать в res/drawable/
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Основной интерфейс лаунчера
                    MainScreen()
                }
            }
        }
}
}
