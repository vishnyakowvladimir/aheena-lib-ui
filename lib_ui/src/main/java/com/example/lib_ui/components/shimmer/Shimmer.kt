package com.example.lib_ui.components.shimmer

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.lib_ui.R
import com.example.lib_ui.theme.AppTheme

private const val SHIMMER_ANIMATION_DURATION = 800
private const val SHIMMER_HIGHLIGHT_WIDTH_DP = 140

interface ShimmerScope {

    @Composable
    fun Shimmer(modifier: Modifier)

    @Composable
    fun Shimmer(
        modifier: Modifier,
        shape: Shape,
    )

    @Composable
    fun ShimmerLight(modifier: Modifier)
}

@Composable
fun ShimmerLayout(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable ShimmerScope.() -> Unit
) = Box(modifier) {
    Box(
        modifier = Modifier.shimmerEffect(),
        contentAlignment = contentAlignment,
        content = { ShimmerScopeImpl().content() },
    )
}

private fun Modifier.shimmerEffect(): Modifier = this.composed {
    var contentWidth by remember { mutableFloatStateOf(0f) }
    val transition = rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -contentWidth,
        targetValue = contentWidth,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SHIMMER_ANIMATION_DURATION, easing = LinearEasing),
        ),
        label = "ShimmerHighlightAnimation",
    )

    val clearColor = AppTheme.palette.background.clear
    val highlight = AppTheme.palette.background.shimmerHighlight

    graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    Brush.horizontalGradient(
                        colors = listOf(clearColor, highlight, clearColor),
                        startX = startOffsetX + contentWidth - SHIMMER_HIGHLIGHT_WIDTH_DP.dp.toPx(),
                        endX = startOffsetX + contentWidth,
                    ),
                    blendMode = BlendMode.SrcAtop,
                )
            }
        }
        .onGloballyPositioned { contentWidth = it.size.width.toFloat() }
}

private class ShimmerScopeImpl : ShimmerScope {

    @Composable
    override fun Shimmer(modifier: Modifier) = Shimmer(
        modifier = modifier,
        shape = RectangleShape,
    )

    @Composable
    override fun Shimmer(
        modifier: Modifier,
        shape: Shape,
    ) = Box(
        modifier.background(
            color = if (isSystemInDarkTheme()) {
                // ставим цвет без прозрачности, чтобы compositingStrategy не искажал цвет блика.
                colorResource(id = R.color.backgroundShimmer)
            } else {
                AppTheme.palette.background.transparent8
            },
            shape = shape,
        ),
    )

    @Composable
    override fun ShimmerLight(modifier: Modifier) = Box(
        modifier.background(
            color = AppTheme.palette.background.transparent8,
        ),
    )
}
