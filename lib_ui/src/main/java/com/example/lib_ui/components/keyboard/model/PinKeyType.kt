package com.example.lib_ui.components.keyboard.model

import androidx.annotation.DrawableRes

sealed interface PinKeyType {
    data class Digit(val value: Int) : PinKeyType
    data class Icon(@DrawableRes val iconRes: Int) : PinKeyType
    data object Empty : PinKeyType
}
