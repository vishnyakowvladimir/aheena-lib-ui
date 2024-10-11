package com.example.lib_ui.theme.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.lib_ui.R

@Stable
data class PaletteIcon(
    val primary: Color,
    val secondary: Color,
    val staticWhite: Color,
)

@Composable
internal fun getPaletteIcon() = PaletteIcon(
    primary = colorResource(id = R.color.paletteIconsPrimary),
    secondary = colorResource(id = R.color.paletteIconsSecondary),
    staticWhite = colorResource(id = R.color.paletteIconsStaticWhite),
)