package com.example.lib_ui.theme.palette

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.res.colorResource
import com.example.lib_ui.R

@Stable
data class PaletteButton(
    val primary: ButtonColors,
    val shimmer: ButtonColors,
)

@Composable
internal fun getPaletteButton() = PaletteButton(
    primary = buttonColors(
        containerColor = colorResource(id = R.color.paletteBackgroundAccent),
        contentColor = colorResource(id = R.color.paletteTextStaticWhite),
        disabledContainerColor = colorResource(id = R.color.paletteBackgroundAccentDisable),
        disabledContentColor = colorResource(id = R.color.paletteTextStaticWhiteDisable),
    ),
    shimmer = buttonColors(
        containerColor = colorResource(id = R.color.backgroundShimmerAlpha08),
    ),
)
