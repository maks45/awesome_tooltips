package com.awesome.tooltips

interface TooltipDataProvider {

    fun tooltipData(tooltip: Tooltip): List<TooltipData>

    fun addTooltipData(tooltip: Tooltip, data: TooltipData)

    fun removeTooltipData(tooltip: Tooltip)

    fun clearDataMap()

}
