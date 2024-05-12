package com.awesome.tooltips.contentProvider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.mask.MaskRef

interface TooltipElementContentProvider {
    fun contentForMask(maskRef: MaskRef): @Composable (Modifier) -> Unit
}
