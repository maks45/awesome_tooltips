package com.awesome.tooltips.data

import com.awesome.tooltips.scene.TooltipScene

object DefaultTooltipDataProvider : TooltipDataProvider {
    private val tooltipSceneOffsetMap = mutableMapOf<TooltipScene, MutableList<TooltipData>>()

    override fun tooltipData(tooltipScene: TooltipScene): List<TooltipData> {
        return tooltipSceneOffsetMap.getOrDefault(tooltipScene, emptyList())
    }

    override fun addTooltipData(tooltipScene: TooltipScene, data: TooltipData) {
        tooltipSceneOffsetMap[tooltipScene]?.add(data) ?: run {
            tooltipSceneOffsetMap[tooltipScene] = mutableListOf(data)
        }
    }

    override fun removeTooltipData(tooltipScene: TooltipScene) {
        tooltipSceneOffsetMap.remove(tooltipScene)
    }

    override fun clearDataMap() {
        tooltipSceneOffsetMap.clear()
    }

}
