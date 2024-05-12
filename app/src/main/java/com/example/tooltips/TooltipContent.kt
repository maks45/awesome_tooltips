package com.example.tooltips

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextTooltip(modifier: Modifier = Modifier) {
    val alphaTransition = rememberInfiniteTransition(label = "bg_color_transition")
    val tooltipAlpha = alphaTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000, delayMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bg_color_animation"
    )
    Column(
        modifier = modifier
            .alpha(tooltipAlpha.value)
            .background(color = Color.Red.copy(alpha = .5f))
    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "This is tooltip for the text"
        )
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun ButtonTooltip(modifier: Modifier = Modifier) {
    val alphaTransition = rememberInfiniteTransition(label = "bg_color_transition")
    val tooltipAlpha = alphaTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000, delayMillis = 500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bg_color_animation"
    )
    Column(
        modifier = modifier
            .alpha(tooltipAlpha.value)
            .background(color = Color.Green.copy(alpha = .5f))

    ) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "This is tooltip for the button"
        )
        Spacer(modifier = Modifier.height(80.dp))
    }
}
