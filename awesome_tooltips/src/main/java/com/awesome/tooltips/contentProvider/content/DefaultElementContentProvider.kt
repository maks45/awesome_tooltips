package com.awesome.tooltips.contentProvider.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.tooltips.contentProvider.TooltipElementContentProvider
import com.awesome.tooltips.mask.MaskRef

class DefaultElementContentProvider : TooltipElementContentProvider {
    override fun contentForMask(maskRef: MaskRef): @Composable (Modifier) -> Unit {
        return { DefaultElementContent(it) }
    }
}