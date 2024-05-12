package com.awesome.tooltips

import androidx.compose.ui.Modifier
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
import androidx.compose.ui.unit.dp
import com.awesome.tooltips.data.DefaultTooltipDataProvider
import com.awesome.tooltips.data.TooltipData
import com.awesome.tooltips.data.TooltipDataProvider
import com.awesome.tooltips.mask.MaskRef
import com.awesome.tooltips.mask.MaskType
import com.awesome.tooltips.scene.TooltipScene

fun Modifier.addTooltip(
    tooltipScene: TooltipScene,
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
    tooltipDataProvider.addTooltipData(tooltipScene, data)
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
                    is MaskType.Circle -> drawCircleMask(data = it)
                    is MaskType.Rect -> drawRectMask(data = it)
                    is MaskType.RoundRect -> drawRoundedRectMask(data = it)
                    else -> Unit
                }
            }
        }
}

private fun DrawScope.drawCircleMask(
    data: TooltipData
) {
    val radius = (data.maskType as? MaskType.Circle)?.radius ?: 0.dp
    val radiusPx = density.run { radius.toPx() }
    drawCircle(
        radius = radiusPx,
        center = Offset(data.offset.x + size.width / 2, data.offset.y + size.height / 2),
        color = Color.Transparent,
        blendMode = BlendMode.Clear
    )
}

private fun DrawScope.drawRoundedRectMask(
    data: TooltipData
) {
    val cornerRadius = (data.maskType as? MaskType.RoundRect)?.cornerRadius ?: 0.dp
    val radiusPx = density.run { cornerRadius.toPx() }
    drawRoundRect(
        cornerRadius = CornerRadius(x = radiusPx, y = radiusPx),
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