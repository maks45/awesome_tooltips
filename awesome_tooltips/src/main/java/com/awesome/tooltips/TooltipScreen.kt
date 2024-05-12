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
import com.awesome.tooltips.contentProvider.TooltipElementContentProvider
import com.awesome.tooltips.contentProvider.TooltipSceneContentProvider
import com.awesome.tooltips.contentProvider.content.DefaultElementContentProvider
import com.awesome.tooltips.contentProvider.content.DefaultSceneContentProvider
import com.awesome.tooltips.scene.Empty
import com.awesome.tooltips.scene.TooltipScene
import kotlinx.coroutines.flow.Flow

@Composable
fun TooltipScreen(
    tooltipSceneFlow: Flow<TooltipScene>,
    elementContentProvider: TooltipElementContentProvider = DefaultElementContentProvider(),
    sceneContentProvider: TooltipSceneContentProvider = DefaultSceneContentProvider(),
    dataProvider: TooltipDataProvider = DefaultTooltipDataProvider,
) {
    val tooltipState = tooltipSceneFlow.collectAsState(initial = Empty)
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
        val mainContent = createRef()
        sceneContentProvider.contentForScene(tooltipState.value).invoke(
            Modifier.constrainAs(
                mainContent
            ) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            })
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
            elementContentProvider.contentForMask(it.maskRef).invoke(
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
