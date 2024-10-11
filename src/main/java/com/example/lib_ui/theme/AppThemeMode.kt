package com.example.lib_ui.theme

import androidx.appcompat.app.AppCompatDelegate

enum class AppThemeMode(val mode: Int) {
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM),
    DARK(AppCompatDelegate.MODE_NIGHT_YES),
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO)
}