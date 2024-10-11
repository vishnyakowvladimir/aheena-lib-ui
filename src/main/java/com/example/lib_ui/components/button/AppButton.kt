package com.example.lib_ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.shimmer.ShimmerLayout
import com.example.lib_ui.components.loader.GradientCircularLoader
import com.example.lib_ui.theme.AppTheme
import com.example.lib_ui.theme.local_composition.LocalIndicationRippleBounded

private val height = 48.dp
private val corner = 12.dp
private val horizontalPadding = 16.dp

@Composable
fun AppButton(
    state: AppButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    Row(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(size = corner))
            .background(
                color = state.getColorBackground(),
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndicationRippleBounded.current,
                role = Role.Button,
                enabled = state.isEnabled,
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when {
            state.isShimmer -> {
                ShimmerLayout {
                    Shimmer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                            .clip(RoundedCornerShape(size = corner)),
                    )
                }
            }

            state.isLoading -> {
                ContentLoading(
                    color = state.getColorContent(),
                )
            }

            else -> {
                ContentText(
                    title = state.title,
                    color = state.getColorContent(),
                )
            }
        }
    }
}

@Composable
private fun ContentText(
    title: String,
    color: Color,
) {
    Text(
        text = title,
        color = color,
        style = AppTheme.typography.title2Medium,
        modifier = Modifier.padding(horizontal = horizontalPadding),
    )
}

@Composable
private fun ContentLoading(color: Color) {
    GradientCircularLoader.M(
        color = color,
        modifier = Modifier.padding(horizontal = horizontalPadding),
    )
}