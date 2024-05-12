package com.awesome.tooltips.mask

import androidx.compose.ui.unit.Dp

sealed interface MaskType {
    data object None: MaskType
    data object Rect : MaskType
    data class Circle(val size: Dp) : MaskType
    data class RoundRect(val cornerRadius: Dp) : MaskType
}
