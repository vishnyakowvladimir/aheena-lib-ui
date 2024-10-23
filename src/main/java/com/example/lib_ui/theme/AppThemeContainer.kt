package com.example.lib_ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import com.example.lib_ui.theme.local_composition.LocalIndicationRippleBounded
import com.example.lib_ui.theme.palette.AppPalette
import com.example.lib_ui.theme.palette.LocalPalette
import com.example.lib_ui.theme.palette.getPalette
import com.example.lib_ui.theme.typography.AppTypography
import com.example.lib_ui.theme.typography.LocalTypography
import com.example.lib_ui.theme.typography.ViewScale

@Composable
fun AppThemeContainer(
    viewScale: ViewScale,
    darkTheme: Boolean = isSystemInDarkTheme(),
    palette: AppPalette = getPalette(),
    typography: AppTypography = AppTypography(viewScale = viewScale),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPalette provides palette,
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = getColorScheme(darkTheme = darkTheme),
        ) {
            CompositionLocalProvider(
                LocalIndication provides ripple(
                    color = AppTheme.palette.background.transparent8,
                    radius = 16.dp,
                    bounded = false,
                ),
                LocalIndicationRippleBounded provides ripple(
                    color = AppTheme.palette.background.transparent8,
                    bounded = true,
                ),
                content = content,
            )
        }
    }
}

@Composable
private fun getColorScheme(darkTheme: Boolean): ColorScheme {
    val materialDarkColorPalette = darkColorScheme(
        primary = AppTheme.palette.text.primary,
        secondary = AppTheme.palette.text.secondary,
        surface = AppTheme.palette.background.primary,
        background = AppTheme.palette.background.primaryGrey,
        error = AppTheme.palette.text.error,
        onPrimary = AppTheme.palette.text.staticWhite,
    )

    val materialColorPalette = lightColorScheme(
        primary = AppTheme.palette.text.primary,
        secondary = AppTheme.palette.text.secondary,
        surface = AppTheme.palette.background.primary,
        background = AppTheme.palette.background.primaryGrey,
        error = AppTheme.palette.text.error,
        onPrimary = AppTheme.palette.text.staticWhite,
    )

    return if (darkTheme) {
        materialDarkColorPalette
    } else {
        materialColorPalette
    }
}
