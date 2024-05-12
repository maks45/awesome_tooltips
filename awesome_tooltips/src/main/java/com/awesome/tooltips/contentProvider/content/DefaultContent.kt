package com.awesome.tooltips.contentProvider.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultSceneContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = .5f))
    )
}

@Composable
fun DefaultElementContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(5.dp)
            .background(color = Color.Red.copy(alpha = .5f))
    )
}