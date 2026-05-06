package com.example.gublachko.ui.screens

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.example.gublachko.ui.components.QuickAppsPanel
import com.example.gublachko.ui.components.StatusBar
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()

    val switchPage = {
        coroutineScope.launch {
            val nextPage = if (pagerState.currentPage == 0) 1 else 0
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        // Левая панель (начинается от самого верха)
        QuickAppsPanel(onSwitchPage = switchPage)

        // Правая область: статус-бар + пейджер
        Column(modifier = Modifier.weight(1f).fillMaxSize()) {
            StatusBar()
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, _, _ ->
                            if (pan.x < -50f) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            } else if (pan.x > 50f) {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        }
                    },
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> MediaScreen()
                    1 -> AppsScreen()
                }
            }
        }
    }
}