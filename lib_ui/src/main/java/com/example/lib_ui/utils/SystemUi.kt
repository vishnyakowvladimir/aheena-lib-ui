package com.example.lib_ui.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun SetSystemBarsColor(
    statusBarColor: Color,
    useDarkIcons: Boolean = !isSystemInDarkTheme(),
) {
    val view = LocalView.current
    val darkTheme = isSystemInDarkTheme()
    val window = (LocalContext.current as AppCompatActivity).window

    LaunchedEffect(statusBarColor, useDarkIcons) {
        if (!view.isInEditMode) {
            window.statusBarColor = statusBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }
}