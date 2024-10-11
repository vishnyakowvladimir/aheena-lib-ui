package com.example.lib_ui.components.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lib_ui.theme.AppTheme

data class AppSnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    @DrawableRes val resourceId: Int? = null,
    val status: Status = Status.SUCCESS,
) : SnackbarVisuals {

    enum class Status {
        SUCCESS,
        ERROR,
        ;

        @Composable
        fun getBackground(): Color {
            return when (this) {
                SUCCESS -> AppTheme.palette.element.success
                ERROR -> AppTheme.palette.element.error
            }
        }
    }
}