package com.example.lib_ui.containers

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.lib_ui.theme.AppThemeContainer
import com.example.lib_ui.theme.AppThemeMode
import com.example.lib_ui.theme.typography.ViewScale

@Composable
fun ThemeContainer(
    activity: AppCompatActivity,
    viewScale: ViewScale,
    themeMode: AppThemeMode,
    content: @Composable () -> Unit,
) {
    ApplyThemeMode(
        activity = activity,
        themeMode = themeMode
    )

    AppThemeContainer(viewScale = viewScale) {
        Surface {
            content()
        }
    }
}

@Composable
private fun ApplyThemeMode(
    activity: AppCompatActivity,
    themeMode: AppThemeMode,
) {
    AppCompatDelegate.setDefaultNightMode(themeMode.mode)
    activity.delegate.applyDayNight()
}
