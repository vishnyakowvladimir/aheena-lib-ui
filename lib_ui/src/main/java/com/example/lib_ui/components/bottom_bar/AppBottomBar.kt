package com.example.lib_ui.components.bottom_bar

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.bottom_bar.model.AppBottomBarItemState
import com.example.lib_ui.components.bottom_bar.model.AppBottomBarState
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.theme.AppTheme

private const val ANIMATION_DURATION = 200
private val bottomBarHeight = 72.dp

@Composable
fun AppBottomBar(
    state: AppBottomBarState,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(bottomBarHeight)
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = AppTheme.palette.background.primaryGrey),
        )

        Row(
            modifier = Modifier.background(color = AppTheme.palette.background.primary),
        ) {
            repeat(state.items.size) { index ->
                ItemBlock(
                    state = state.items[index],
                    onClick = { onItemClick(index) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun ItemBlock(
    state: AppBottomBarItemState,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val transition = updateTransition(
            targetState = state.isSelected,
            label = "",
        )

        val animatedColor = transition.animateColor(
            transitionSpec = { tween(ANIMATION_DURATION) },
            label = ""
        ) { isSelected ->
            getColor(isSelected)
        }

        val animatedScale = transition.animateFloat(
            transitionSpec = { tween(ANIMATION_DURATION) },
            label = "",
        ) { isSelected ->
            if (isSelected) 1.2f else 1.0f
        }

        Column(
            modifier = Modifier
                .scale(animatedScale.value),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.iconRes != null) {
                ImageFromSource(
                    modifier = Modifier.clickable {
                        onClick()
                    },
                    iconSource = IconSource.FromResource(
                        resourceId = state.iconRes,
                    ),
                    colorFilter = ColorFilter.tint(color = animatedColor.value),
                )
            }

            if (state.iconRes != null && state.title.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
            }

            if (state.title.isNotEmpty()) {
                Text(
                    text = state.title,
                    color = animatedColor.value,
                    style = AppTheme.typography.text2Regular,
                )
            }
        }
    }
}

@Composable
private fun getColor(isSelected: Boolean): Color {
    return if (isSelected) {
        AppTheme.palette.element.accent
    } else {
        AppTheme.palette.text.secondary
    }
}
