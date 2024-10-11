package com.example.lib_ui.components.input.base.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.lib_ui.R
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.theme.AppTheme
import com.example.lib_ui.utils.AnimatedVisibilityFade
import com.example.lib_ui.utils.AppAnimatedContent

@Composable
internal fun TailBlock(
    iconRes: Int?,
    onIconClick: () -> Unit,
    onResetIconClick: () -> Unit,
    isTextEmpty: Boolean,
) {
    Row {
        AnimatedVisibilityFade(visible = !isTextEmpty) {
            IconBlock(
                iconRes = R.drawable.ic_24dp_actions_cross,
                onClick = onResetIconClick,
            )
        }

        AnimatedVisibilityFade(visible = !isTextEmpty && iconRes != null) {
            SpacerHorizontal()
        }

        if (iconRes != null) {
            AppAnimatedContent(iconRes) { res ->
                IconBlock(
                    iconRes = res,
                    onClick = onIconClick,
                )
            }

        }
    }
}

@Composable
private fun IconBlock(
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {
    ImageFromSource(
        modifier = Modifier.clickable {
            onClick()
        },
        iconSource = IconSource.FromResource(
            resourceId = iconRes,
        ),
        colorFilter = ColorFilter.tint(color = AppTheme.palette.background.transparent50),
    )
}

@Composable
private fun SpacerHorizontal() {
    Spacer(modifier = Modifier.width(4.dp))
}