package com.awesome.tooltips

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import com.awesome.tooltips.mask.MaskRef
import com.awersome.tooltips.mask.MaskType

fun Modifier.addTooltip(
    tooltip: Tooltip,
    maskRef: MaskRef,
    maskType: MaskType = MaskType.Rect,
    tooltipDataProvider: TooltipDataProvider = DefaultTooltipDataProvider
): Modifier = this.onGloballyPositioned {
        val left = it.boundsInRoot().left
        val top = it.boundsInRoot().top
        val width = it.boundsInRoot().width
        val height = it.boundsInRoot().height
        val data = TooltipData(
            offset = Offset(x = left, y = top),
            size = Size(width = width, height = height),
            maskType = maskType,
            maskRef = maskRef
        )
        tooltipDataProvider.addTooltipData(tooltip, data)
    }

fun Modifier.drawMasks(
    dataList: List<TooltipData>,
): Modifier {
    return this
        .graphicsLayer {
            alpha = .99f
        }
        .drawWithContent {
            drawContent()
            dataList.onEach {
                when (it.maskType) {
                    //todo
                    is MaskType.None -> Unit
                    is MaskType.RoundRect -> drawRoundedRectMask(data = it)
                    else -> drawRectMask(data = it)
                }
            }
        }
}

private fun DrawScope.drawRoundedRectMask(
    data: TooltipData,
) {
    val cornerRadius = (data.maskType as? MaskType.RoundRect) ?.cornerRadius ?: 0f
    drawRoundRect(
        cornerRadius = CornerRadius(x = cornerRadius, y = cornerRadius),
        topLeft = Offset(data.offset.x, data.offset.y),
        size = Size(height = data.size.height, width = data.size.width),
        color = Color.Transparent,
        blendMode = BlendMode.Clear
    )
}

private fun DrawScope.drawRectMask(
    data: TooltipData
) {
    drawRect(
        topLeft = Offset(data.offset.x, data.offset.y),
        size = Size(height = data.size.height, width = data.size.width),
        color = Color.Transparent,
        blendMode = BlendMode.Clear
    )
}