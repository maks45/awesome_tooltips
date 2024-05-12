package com.awesome.tooltips.mask

sealed interface MaskType {
    data object None: MaskType
    data object Rect : MaskType
    data class Circle(val sizeDp: Int) : MaskType
    data class RoundRect(val cornerRadiusDp: Int) : MaskType
}
