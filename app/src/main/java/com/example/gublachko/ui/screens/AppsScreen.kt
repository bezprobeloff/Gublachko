package com.example.gublachko.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gublachko.ui.components.AppIcon
import com.example.gublachko.utils.getInstalledApps
import com.example.gublachko.utils.launchApp

@Composable
fun AppsScreen() {
    val context = LocalContext.current
    val allApps = remember { getInstalledApps(context) }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(allApps) { app ->
            AppIcon(app) {
                launchApp(context, app.packageName)
            }
        }
    }
}