package com.example.lib_ui.components.input.base.view

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.input.base.LocalBaseInputTypography
import com.example.lib_ui.theme.AppTheme
import com.example.lib_ui.utils.ANIMATION_DURATION_DEFAULT

private val defaultOffsetX = (-4).dp
private val defaultOffsetY = (-20).dp

@Composable
internal fun AppInputPlaceholder(
    text: String,
    isFocused: Boolean,
    isInputTextEmpty: Boolean,
    offsetX: Dp?,
) {
    val transition = updateTransition(
        targetState = isFocused || !isInputTextEmpty,
        label = "",
    )

    val offsetXState = transition.animateDp(
        transitionSpec = { tween(ANIMATION_DURATION_DEFAULT) },
        label = "",
    ) { needMoved ->
        if (needMoved) (offsetX ?: defaultOffsetX) else 0.dp
    }

    val offsetYState = transition.animateDp(
        transitionSpec = { tween(ANIMATION_DURATION_DEFAULT) },
        label = "",
    ) { needMoved ->
        if (needMoved) defaultOffsetY else 0.dp
    }

    val animatedColor = transition.animateColor(
        transitionSpec = { tween(ANIMATION_DURATION_DEFAULT) },
        label = "",
    ) { needMoved ->
        getTextColor(needMoved)
    }

    val animatedTextStyleScale = transition.animateFloat(
        transitionSpec = { tween(ANIMATION_DURATION_DEFAULT) },
        label = "",
    ) { needMoved ->
        if (needMoved) 1f else 0f
    }

    Text(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetXState.value.roundToPx(),
                    y = offsetYState.value.roundToPx(),
                )
            },
        text = text,
        color = animatedColor.value,
        style = lerp(
            start = LocalBaseInputTypography.current,
            stop = LocalBaseInputTypography.current
                .copy(
                    fontSize = LocalBaseInputTypography.current.fontSize * 0.8,
                    lineHeight = LocalBaseInputTypography.current.lineHeight * 0.8,
                ),
            fraction = animatedTextStyleScale.value,
        ),
    )
}

@Composable
private fun getTextColor(
    isFocused: Boolean,
): Color {
    return if (isFocused) {
        AppTheme.palette.element.accent
    } else {
        AppTheme.palette.background.transparent50
    }
}


