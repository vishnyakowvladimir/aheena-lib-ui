package com.example.lib_ui.theme.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.lib_ui.R

@Stable
data class PaletteElement(
    val primary: Color,
    val accent: Color,
    val success: Color,
    val error: Color,
    val errorTransparent50: Color,
)

@Composable
internal fun getPaletteElement() = PaletteElement(
    primary = colorResource(id = R.color.paletteElementsPrimary),
    accent = colorResource(id = R.color.paletteElementsAccent),
    success = colorResource(id = R.color.paletteElementsSuccess),
    error = colorResource(id = R.color.paletteElementsError),
    errorTransparent50 = colorResource(id = R.color.paletteElementsErrorTransparent50),
)