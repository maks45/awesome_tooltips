package com.awesome.tooltips

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.flow.Flow

@Composable
fun TooltipScreen(
    tooltipFlow: Flow<Tooltip>,
    contentProvider: TooltipContentProvider,
    dataProvider: TooltipDataProvider = DefaultTooltipDataProvider,
) {
    val tooltipState = tooltipFlow.collectAsState(initial = Empty)
    val dataList = dataProvider.tooltipData(tooltipState.value)

    DisposableEffect(key1 = Unit) {
        onDispose {
            dataProvider.clearDataMap()
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .drawMasks(dataList = dataList)
    ) {
        dataList.onEach {
            val (target, content) = createRefs()
            val width = with(LocalDensity.current) { it.size.width.toDp() }
            val height = with(LocalDensity.current) { it.size.height.toDp() }
            val offsetX = with(LocalDensity.current) { it.offset.x.toDp() }
            val offsetY = with(LocalDensity.current) { it.offset.y.toDp() }
            Box(
                modifier = Modifier
                    .size(width, height)
                    .constrainAs(target) {
                        start.linkTo(parent.start, offsetX)
                        top.linkTo(parent.top, offsetY)
                    }
            )
            contentProvider.contentForMask(it.maskRef).invoke(
                Modifier.constrainAs(content) {
                    start.linkTo(target.start)
                    top.linkTo(target.top)
                    end.linkTo(target.end)
                    bottom.linkTo(target.bottom)
                }
            )
        }
    }
}
