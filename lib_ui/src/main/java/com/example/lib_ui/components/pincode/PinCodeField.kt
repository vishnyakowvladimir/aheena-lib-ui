package com.example.lib_ui.components.pincode

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.pincode.model.PinCodeFieldState
import com.example.lib_ui.components.pincode.model.PinCodeFieldType
import com.example.lib_ui.components.pincode.model.PinCodeItemState
import com.example.lib_ui.theme.AppTheme

private const val ERROR_SHAKE_ANIMATION_ITERATIONS_COUNT = 6
private const val ERROR_SHAKE_ANIMATION_ITERATION_DURATION = 100
private const val ERROR_SHAKE_ANIMATION_OFFSET = 8f

private const val ANIMATION_SCALE = 1.2f
private const val LOADING_SCALE_ANIMATION_DURATION = 400

private const val COLOR_ANIMATION_DURATION = 100
private const val SELECT_SCALE_ANIMATION_DURATION = COLOR_ANIMATION_DURATION * 2

@Composable
fun PinCodeField(
    state: PinCodeFieldState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (state.title.isNotEmpty()) {
            TitleBlock(title = state.title)

            Spacer(modifier = Modifier.height(20.dp))
        }

        Dots(state = state)

        Spacer(modifier = Modifier.height(12.dp))

        ErrorBlock(
            text = (state.type as? PinCodeFieldType.Error)?.errorText ?: ""
        )
    }
}

@Composable
private fun TitleBlock(title: String) {
    Text(
        text = title,
        color = AppTheme.palette.text.secondary,
        style = AppTheme.typography.button1Regular,
    )
}

@Composable
private fun ErrorBlock(text: String) {
    Text(
        text = text,
        color = AppTheme.palette.element.error,
        style = AppTheme.typography.text2Regular,
    )
}

@Composable
private fun Dots(
    state: PinCodeFieldState,
) {
    val animation = remember {
        TargetBasedAnimation(
            animationSpec = repeatable(
                iterations = ERROR_SHAKE_ANIMATION_ITERATIONS_COUNT,
                animation = tween(
                    durationMillis = ERROR_SHAKE_ANIMATION_ITERATION_DURATION,
                    easing = LinearEasing,
                ),
                repeatMode = RepeatMode.Reverse,
            ),
            typeConverter = Float.VectorConverter,
            initialValue = -ERROR_SHAKE_ANIMATION_OFFSET,
            targetValue = ERROR_SHAKE_ANIMATION_OFFSET,
        )
    }

    var playTime by remember { mutableLongStateOf(0L) }
    var animationValue by remember { mutableIntStateOf(0) }

    LaunchedEffect(state.type is PinCodeFieldType.Error) {
        if (state.type is PinCodeFieldType.Error) {
            val startTime = withFrameNanos { frameTimeNanos -> frameTimeNanos }
            do {
                playTime = withFrameNanos { frameTimeNanos -> frameTimeNanos } - startTime
                animationValue = animation.getValueFromNanos(playTime).toInt()
            } while (!animation.isFinishedFromNanos(playTime))
            animationValue = 0
        }
    }

    Row(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = animationValue.dp.roundToPx(),
                    y = 0,
                )
            },
        horizontalArrangement = Arrangement.Center,
    ) {
        SpacerHorizontal()

        repeat(state.maxIndex.index.inc()) { index ->
            PinCodeItem(
                itemState = getPinCodeItemState(
                    fieldState = state,
                    index = index,
                )
            )

            SpacerHorizontal()
        }
    }
}

@Composable
private fun PinCodeItem(itemState: PinCodeItemState) {
    val animatedColor = animateColorAsState(
        targetValue = getColor(itemState),
        animationSpec = tween(COLOR_ANIMATION_DURATION),
        label = "",
    )

    val animatedScale = remember { Animatable(1f, Float.VectorConverter) }

    LaunchedEffect(itemState) {
        when {
            itemState is PinCodeItemState.Loading -> {
                animatedScale.snapTo(1f)
                animatedScale.animateTo(
                    targetValue = ANIMATION_SCALE,
                    animationSpec = infiniteRepeatable(
                        animation = tween(LOADING_SCALE_ANIMATION_DURATION),
                        repeatMode = RepeatMode.Reverse,
                    ),
                )
            }

            (itemState as? PinCodeItemState.Default)?.isCurrent ?: false -> {
                animatedScale.snapTo(1f)
                animatedScale.animateTo(
                    targetValue = ANIMATION_SCALE,
                    animationSpec = tween(SELECT_SCALE_ANIMATION_DURATION),
                )

                animatedScale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(SELECT_SCALE_ANIMATION_DURATION),
                )
            }

            else -> {
                animatedScale.stop()
                animatedScale.snapTo(1f)
            }
        }
    }

    Box(
        modifier = Modifier
            .scale(animatedScale.value)
            .size(16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = animatedColor.value),
    )
}

@Composable
private fun SpacerHorizontal() {
    Spacer(modifier = Modifier.width(40.dp))
}

private fun getPinCodeItemState(
    fieldState: PinCodeFieldState,
    index: Int,
): PinCodeItemState {
    return when (val type = fieldState.type) {
        is PinCodeFieldType.Error -> {
            PinCodeItemState.Error
        }

        is PinCodeFieldType.Loading -> {
            PinCodeItemState.Loading
        }

        is PinCodeFieldType.Default -> {
            PinCodeItemState.Default(
                isBefore = index < type.selectedIndex.index,
                isCurrent = index == type.selectedIndex.index,
            )
        }
    }
}

@Composable
private fun getColor(itemState: PinCodeItemState): Color {
    return when {
        itemState is PinCodeItemState.Error -> {
            AppTheme.palette.element.error
        }

        itemState is PinCodeItemState.Loading -> {
            AppTheme.palette.element.success
        }

        itemState is PinCodeItemState.Default && (itemState.isBefore || itemState.isCurrent) -> {
            AppTheme.palette.element.accent
        }

        else -> {
            AppTheme.palette.background.transparent30
        }
    }
}
