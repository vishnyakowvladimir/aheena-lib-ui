package com.example.lib_ui.theme.palette

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.colorResource
import com.example.lib_ui.R

@Stable
class PaletteBackground(
    val primary: Color,
    val primaryGrey: Color,
    val transparent8: Color,
    val transparent30: Color,
    val transparent50: Color,
    val clear: Color,
    val shimmerHighlight: Color,
)

@Composable
internal fun getPaletteBackground() = PaletteBackground(
    primary = colorResource(id = R.color.paletteBackgroundPrimary),
    primaryGrey = colorResource(id = R.color.paletteBackgroundPrimaryGrey),
    transparent8 = colorResource(id = R.color.paletteBackgroundTransparent8),
    transparent30 = colorResource(id = R.color.paletteBackgroundTransparent30),
    transparent50 = colorResource(id = R.color.paletteBackgroundTransparent50),
    clear = colorResource(id = R.color.backgroundClear),
    shimmerHighlight = colorResource(id = R.color.shimmerHighlight),
)