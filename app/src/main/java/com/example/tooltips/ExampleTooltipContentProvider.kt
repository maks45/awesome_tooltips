package com.example.tooltips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.awesome.tooltips.TooltipContentProvider
import com.awesome.tooltips.mask.MaskRef

object ExampleTooltipContentProvider : TooltipContentProvider {
    override fun contentForMask(maskRef: MaskRef): @Composable (Modifier) -> Unit {
        return when (maskRef) {
            is ButtonMaskRef -> { modifier -> ButtonTooltip(modifier) }
            is TextMaskRef -> { modifier -> TextTooltip(modifier) }

            else -> { _ -> Unit }
        }
    }
}