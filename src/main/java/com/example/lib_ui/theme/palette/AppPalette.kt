package com.example.lib_ui.theme.palette

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
class AppPalette(
    val text: PaletteText,
    val button: PaletteButton,
    val background: PaletteBackground,
    val icon: PaletteIcon,
    val element: PaletteElement,
)

@Composable
internal fun getPalette() = AppPalette(
    text = getPaletteText(),
    button = getPaletteButton(),
    background = getPaletteBackground(),
    icon = getPaletteIcon(),
    element = getPaletteElement(),
)

internal val LocalPalette = staticCompositionLocalOf<AppPalette> {
    error("CompositionLocal LocalPalette not present")
}