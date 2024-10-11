package com.example.lib_ui.theme.palette

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.colorResource
import com.example.lib_ui.R

@Stable
class PaletteText(
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val error: Color,
    val staticWhite: Color,
)

@Composable
internal fun getPaletteText() = PaletteText(
    primary = colorResource(id = R.color.paletteTextPrimary),
    secondary = colorResource(id = R.color.paletteTextSecondary),
    accent = colorResource(id = R.color.paletteTextAccent),
    error = colorResource(id = R.color.paletteTextError),
    staticWhite = colorResource(id = R.color.paletteTextStaticWhite),
)