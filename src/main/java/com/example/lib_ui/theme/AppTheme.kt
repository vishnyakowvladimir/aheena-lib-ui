package com.example.lib_ui.theme

import androidx.compose.runtime.Composable
import com.example.lib_ui.theme.palette.LocalPalette
import com.example.lib_ui.theme.palette.AppPalette
import androidx.compose.runtime.ReadOnlyComposable
import com.example.lib_ui.theme.typography.AppTypography
import com.example.lib_ui.theme.typography.LocalTypography


object AppTheme {

    val palette: AppPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalPalette.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}