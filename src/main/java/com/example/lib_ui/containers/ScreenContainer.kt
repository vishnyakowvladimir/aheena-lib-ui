package com.example.lib_ui.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lib_ui.theme.AppTheme
import com.example.lib_ui.utils.SetSystemBarsColor

@Composable
fun ScreenContainer(
    backgroundContent: Color = AppTheme.palette.background.primary,
    backgroundStatusBar: Color = AppTheme.palette.background.primary,
    topBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    SetSystemBarsColor(
        statusBarColor = backgroundStatusBar,
    )

    Scaffold(
        topBar = topBar,
        snackbarHost = snackbarHost,
        modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
        containerColor = backgroundContent,
    )
    { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            content()
        }
    }
}