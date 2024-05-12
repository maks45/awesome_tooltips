package com.awesome.tooltips

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.awesome.tooltips.mask.MaskRef
import com.awersome.tooltips.mask.MaskType

data class TooltipData(
    val offset: Offset,
    val size: Size,
    val maskType: MaskType,
    val maskRef: MaskRef
)
