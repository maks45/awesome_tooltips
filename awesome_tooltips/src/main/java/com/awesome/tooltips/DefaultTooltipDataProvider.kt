package com.awesome.tooltips

object DefaultTooltipDataProvider : TooltipDataProvider {
    private val tooltipOffsetMap = mutableMapOf<Tooltip, MutableList<TooltipData>>()

    override fun tooltipData(tooltip: Tooltip): List<TooltipData> {
        return tooltipOffsetMap.getOrDefault(tooltip, emptyList())
    }

    override fun addTooltipData(tooltip: Tooltip, data: TooltipData) {
        tooltipOffsetMap[tooltip]?.add(data) ?: run {
            tooltipOffsetMap[tooltip] = mutableListOf(data)
        }
    }

    override fun removeTooltipData(tooltip: Tooltip) {
        tooltipOffsetMap.remove(tooltip)
    }

    override fun clearDataMap() {
        tooltipOffsetMap.clear()
    }

}
