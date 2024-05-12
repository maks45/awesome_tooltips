package com.awesome.tooltips.data

import com.awesome.tooltips.scene.TooltipScene

interface TooltipDataProvider {

    fun tooltipData(tooltipScene: TooltipScene): List<TooltipData>

    fun addTooltipData(tooltipScene: TooltipScene, data: TooltipData)

    fun removeTooltipData(tooltipScene: TooltipScene)

    fun clearDataMap()

}
