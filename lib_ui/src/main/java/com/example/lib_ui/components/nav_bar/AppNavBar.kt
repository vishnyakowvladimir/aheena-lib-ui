package com.example.lib_ui.components.nav_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.theme.AppTheme

private val height: Dp = 44.dp

@Composable
fun AppNavBar(
    state: AppNavBarState,
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = state.backgroundColor)
            .height(height),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LeftPartBlock(
            state = state.leftPart,
        )

        MiddlePartBlock(
            state = state.middlePart,
            modifier = Modifier.weight(1f),
        )

        RightPartBlock(
            state = state.rightPart,
        )
    }
}

@Composable
fun LeftPartBlock(
    state: AppNavBarState.LeftPart?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(height)
            .clickable(
                enabled = state != null,
                onClick = { state?.onClick?.invoke() }
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (state != null) {
            ImageFromSource(
                iconSource = IconSource.FromResource(
                    resourceId = state.iconRes,
                ),
                colorFilter = ColorFilter.tint(color = state.elementColor),
            )
        }
    }
}

@Composable
fun RightPartBlock(
    state: AppNavBarState.RightPart?,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier
            .size(height)
            .clickable(
                enabled = state != null,
                onClick = { state?.onClick?.invoke() }
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (state != null) {
            ImageFromSource(
                iconSource = IconSource.FromResource(
                    resourceId = state.iconRes,
                ),
                colorFilter = ColorFilter.tint(color = state.elementColor),
            )
        }
    }
}

@Composable
fun MiddlePartBlock(
    state: AppNavBarState.MiddlePart,
    modifier: Modifier = Modifier,
) {
    Text(
        text = state.title,
        modifier = modifier,
        color = state.elementColor,
        style = AppTheme.typography.title2Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
    )
}