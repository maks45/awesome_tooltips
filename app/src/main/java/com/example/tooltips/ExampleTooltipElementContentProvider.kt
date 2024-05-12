package com.example.tooltips

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.contentProvider.TooltipElementContentProvider
import com.awesome.tooltips.mask.MaskRef

object ExampleTooltipElementContentProvider : TooltipElementContentProvider {
    override fun contentForMask(maskRef: MaskRef): @Composable (Modifier) -> Unit {
        return when (maskRef) {
            is ButtonMaskRef -> { modifier -> ButtonTooltip(modifier) }
            is TextMaskRef -> { modifier -> TextTooltip(modifier) }

            else -> { _ -> Unit }
        }
    }
}