package com.example.lib_ui.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.runtime.Composable

const val ANIMATION_DURATION_DEFAULT = 500

@Composable
fun AnimatedVisibilityFade(
    visible: Boolean,
    duration: Int = ANIMATION_DURATION_DEFAULT,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(duration)),
        exit = fadeOut(tween(duration)),
    ) {
        content()
    }
}

@Composable
fun AnimatedVisibilityFadeAndExpandVertically(
    visible: Boolean,
    duration: Int = ANIMATION_DURATION_DEFAULT,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(duration)) + expandVertically(tween(duration)),
        exit = fadeOut(tween(duration)) + shrinkVertically(tween(duration)),
    ) {
        content()
    }
}

@Composable
fun <T> AppAnimatedContent(
    state: T,
    duration: Int = ANIMATION_DURATION_DEFAULT,
    content: @Composable (state: T) -> Unit,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            fadeIn(animationSpec = tween(duration)) +
                    scaleIn(animationSpec = tween(duration)) togetherWith
                    fadeOut(animationSpec = tween(duration))
        },
        label = ""
    ) { result ->
        content(result)
    }
}