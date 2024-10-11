package com.example.lib_ui.components.loader

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lib_ui.R
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.theme.AppTheme

private const val LOADER_ROTATION_DURATION = 1400

sealed class GradientCircularLoader {

    @get:Composable
    abstract val size: Dp

    abstract val resourceId: Int

    @Composable
    private fun rotationAngle(): State<Float> {
        val transition = rememberInfiniteTransition(label = "")
        return transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = LOADER_ROTATION_DURATION
                }
            ),
            label = "",
        )
    }

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier,
        color: Color? = null,
    ) = Box(
        modifier = modifier,
    ) {
        val angle by rotationAngle()
        ImageFromSource(
            modifier = Modifier
                .size(size = size)
                .rotate(angle),
            iconSource = IconSource.FromResource(
                resourceId = resourceId,
                contentDescription = null,
            ),
            colorFilter = ColorFilter.tint(color ?: AppTheme.palette.text.accent),
        )
    }

    data object M : GradientCircularLoader() {

        override val size: Dp
            @Composable
            get() = 20.dp

        override val resourceId: Int
            get() = R.drawable.il_20dp_gradient_circular_loader
    }

    data object XL : GradientCircularLoader() {

        override val size: Dp
            @Composable
            get() = 40.dp

        override val resourceId: Int
            get() = R.drawable.il_40dp_gradient_circular_loader
    }
}