package com.awesome.tooltips.scene

import kotlinx.coroutines.flow.Flow

interface SceneFlowProvider {
    val tooltipsSceneFlow: Flow<TooltipScene>
}