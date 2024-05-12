package com.awesome.tooltips

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.mask.MaskRef

interface TooltipContentProvider {
    fun contentForMask(maskRef: MaskRef): @Composable (Modifier) -> Unit
}
