//package com.example.gulauncher.ui.components
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.asImageBitmap
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.core.graphics.drawable.toBitmap
//import com.example.gulauncher.data.models.AppInfo
//
//@Composable
//fun AppIcon(app: AppInfo, onClick: () -> Unit) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .clickable { onClick() }
//            .padding(8.dp)
//    ) {
//        androidx.compose.foundation.Image(
//            bitmap = app.icon.toBitmap().asImageBitmap(),
//            contentDescription = app.name,
//            modifier = Modifier.size(96.dp)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(
//            text = app.name,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            maxLines = 2,
//            modifier = Modifier.fillMaxWidth()
//        )
//    }
//}


package com.example.gublachko.ui.components

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gublachko.data.models.AppInfo

@Composable
fun AppIcon(app: AppInfo, onClick: () -> Unit) {
    // Преобразуем Drawable в Bitmap (учитываем плотность экрана)
    val bitmap = remember(app.icon) {
        drawableToBitmap(app.icon, width = 120, height = 120)
    }
    val painter = remember(bitmap) { BitmapPainter(bitmap.asImageBitmap()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
            .width(80.dp)  // фиксированная ширина для выравнивания
    ) {
        // Иконка в стиле CarPlay: тёмный фон, скруглённый квадрат
        Box(
            modifier = Modifier
                .size(128.dp)
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
            Icon(
                painter = painter,
                contentDescription = app.name,
                modifier = Modifier
                    .fillMaxSize()
                .padding(0.dp),   // отступ, чтобы иконка не касалась краёв
                tint = Color.Unspecified // сохраняем оригинальные цвета
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = app.name,
            fontSize = 16.sp,
            color = Color.Black,   // чёрный текст
            maxLines = 2,
            softWrap = true,
            modifier = Modifier
                .background(
                    color = Color.White.copy(alpha = 0.5f), // белый с лёгкой прозрачностью, чтобы не перекрывать фон полностью
                    shape = RoundedCornerShape(6.dp)        // скругление для плашки
                )
                .padding(horizontal = 4.dp, vertical = 2.dp) // минимальные внутренние отступы
        )
    }
}

/**
 * Конвертирует Drawable в Bitmap заданного размера, центрируя и масштабируя с сохранением пропорций.
 */
private fun drawableToBitmap(drawable: Drawable, width: Int, height: Int): Bitmap {
    if (drawable is BitmapDrawable && drawable.bitmap != null) {
        return Bitmap.createScaledBitmap(drawable.bitmap, width, height, true)
    }
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, width, height)
    drawable.draw(canvas)
    return bitmap
}