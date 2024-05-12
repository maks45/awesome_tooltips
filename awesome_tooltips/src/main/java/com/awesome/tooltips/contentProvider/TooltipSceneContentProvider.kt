package com.awesome.tooltips.contentProvider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.scene.TooltipScene

interface TooltipSceneContentProvider {
    fun contentForScene(tooltipScene: TooltipScene): @Composable (Modifier) -> Unit

}
