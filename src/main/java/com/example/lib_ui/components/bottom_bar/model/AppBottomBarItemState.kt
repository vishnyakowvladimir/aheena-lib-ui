package com.example.lib_ui.components.bottom_bar.model

import androidx.annotation.DrawableRes

data class AppBottomBarItemState(
    val title: String = "",
    @DrawableRes val iconRes: Int? = null,
    val isSelected: Boolean = false,
)
