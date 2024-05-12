package com.awesome.tooltips.contentProvider.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.contentProvider.TooltipSceneContentProvider
import com.awesome.tooltips.scene.TooltipScene

class DefaultSceneContentProvider: TooltipSceneContentProvider {
    override fun contentForScene(tooltipScene: TooltipScene): @Composable (Modifier) -> Unit {
        return { DefaultSceneContent(it) }
    }
}