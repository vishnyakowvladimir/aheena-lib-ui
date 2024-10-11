package com.example.lib_ui.components.nav_bar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lib_ui.theme.AppTheme

data class AppNavBarState(
    val leftPart: LeftPart?,
    val middlePart: MiddlePart,
    val rightPart: RightPart?,
    val backgroundColor: Color,
) {
    data class LeftPart(
        @DrawableRes val iconRes: Int,
        val elementColor: Color,
        val onClick: () -> Unit,
    ) {
        companion object {

            @Composable
            operator fun invoke(
                @DrawableRes iconRes: Int,
                elementColor: Color = AppTheme.palette.icon.primary,
                onClick: () -> Unit = {},
            ): LeftPart {
                return LeftPart(
                    iconRes = iconRes,
                    elementColor = elementColor,
                    onClick = onClick,
                )
            }
        }
    }

    data class MiddlePart(
        val title: String,
        val elementColor: Color,
    ) {
        companion object {

            @Composable
            operator fun invoke(
                title: String,
                elementColor: Color = AppTheme.palette.icon.primary,
            ): MiddlePart {
                return MiddlePart(
                    title = title,
                    elementColor = elementColor,
                )
            }
        }
    }

    data class RightPart(
        @DrawableRes val iconRes: Int,
        val elementColor: Color,
        val onClick: () -> Unit,
    ) {
        companion object {

            @Composable
            operator fun invoke(
                @DrawableRes iconRes: Int,
                elementColor: Color = AppTheme.palette.icon.primary,
                onClick: () -> Unit = {},
            ): RightPart {
                return RightPart(
                    iconRes = iconRes,
                    elementColor = elementColor,
                    onClick = onClick,
                )
            }
        }
    }
}