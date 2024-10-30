package com.example.lib_ui.components.button

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lib_ui.theme.AppTheme

data class AppButtonState(
    val title: String,
    val isEnabled: Boolean = true,
    val isLoading: Boolean = false,
    val isShimmer: Boolean = false,
    val color: ButtonColor = ButtonColor.PRIMARY,
) {

    @Composable
    fun getColorContent(): Color {
        return when {
            isEnabled -> color.getColorBackground().contentColor
            else -> color.getColorBackground().disabledContentColor
        }
    }

    @Composable
    fun getColorBackground(): Color {
        return when {
            isEnabled -> color.getColorBackground().containerColor
            else -> color.getColorBackground().disabledContainerColor
        }
    }

    enum class ButtonColor {
        PRIMARY,
        ;

        @Composable
        fun getColorBackground(): ButtonColors {
            return when (this) {
                PRIMARY -> AppTheme.palette.button.primary
                else -> AppTheme.palette.button.primary
            }
        }

        @Composable
        fun getColorContent(): ButtonColors {
            return when (this) {
                PRIMARY -> AppTheme.palette.button.primary
                else -> AppTheme.palette.button.primary
            }
        }
    }
}
