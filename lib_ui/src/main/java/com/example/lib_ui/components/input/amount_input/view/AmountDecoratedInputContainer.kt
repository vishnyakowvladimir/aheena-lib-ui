package com.example.lib_ui.components.input.amount_input.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.lib_ui.R
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.components.input.amount_input.LocalAmountInputCurrencySymbol
import com.example.lib_ui.components.input.amount_input.LocalAmountInputPlaceholderColor
import com.example.lib_ui.components.input.amount_input.LocalAmountInputTextTypography
import com.example.lib_ui.components.input.base.model.InputErrorState
import com.example.lib_ui.theme.AppTheme
import com.example.lib_ui.utils.ANIMATION_DURATION_DEFAULT
import com.example.lib_ui.utils.AnimatedVisibilityFade
import com.example.lib_ui.utils.AnimatedVisibilityFadeAndExpandVertically

@Composable
internal fun AmountDecoratedInputContainer(
    textFieldValue: TextFieldValue,
    title: String,
    onResetIconClick: () -> Unit,
    innerTextField: @Composable () -> Unit,
    errorState: InputErrorState,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        if (title.isNotEmpty()) {
            TitleBlock(text = title)
        }

        InputContent(
            textFieldValue = textFieldValue,
            innerTextField = innerTextField,
            onResetIconClick = onResetIconClick,
            isError = errorState.isError,
        )

        SpacerVertical()

        ErrorTextBlock(errorState = errorState)
    }
}

@Composable
private fun TitleBlock(text: String) {
    Text(
        modifier = Modifier.padding(bottom = 4.dp),
        text = text,
        color = AppTheme.palette.text.secondary,
        style = AppTheme.typography.text2Regular
    )
}

@Composable
private fun InputContent(
    textFieldValue: TextFieldValue,
    innerTextField: @Composable () -> Unit,
    onResetIconClick: () -> Unit,
    isError: Boolean,
) {
    val animatedColor = animateColorAsState(
        targetValue = getBackgroundColor(isError),
        animationSpec = tween(ANIMATION_DURATION_DEFAULT),
        label = "",
    )

    Row(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = animatedColor.value)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (textFieldValue.text.isEmpty()) {
                InputPlaceholder()
            }

            innerTextField()
        }

        TailBlock(
            onResetIconClick = onResetIconClick,
            isTextEmpty = textFieldValue.text.isEmpty(),
        )
    }
}

@Composable
private fun ErrorTextBlock(
    errorState: InputErrorState,
) {
    Box(
        modifier = Modifier
            .height(16.dp)
            .padding(start = 8.dp)
    ) {
        AnimatedVisibilityFadeAndExpandVertically(
            visible = errorState.isError,
        ) {
            Text(
                text = errorState.textError,
                color = AppTheme.palette.element.error,
                style = AppTheme.typography.text2Regular,
            )
        }
    }
}

@Composable
private fun TailBlock(
    onResetIconClick: () -> Unit,
    isTextEmpty: Boolean,
) {
    AnimatedVisibilityFade(visible = !isTextEmpty) {
        IconBlock(
            iconRes = R.drawable.ic_24dp_actions_cross,
            onClick = onResetIconClick,
        )
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
private fun InputPlaceholder() {
    Text(
        text = "0 ${LocalAmountInputCurrencySymbol.current}",
        color = LocalAmountInputPlaceholderColor.current,
        style = LocalAmountInputTextTypography.current,
    )
}

@Composable
private fun SpacerVertical() {
    Spacer(modifier = Modifier.height(2.dp))
}

@Composable
private fun getBackgroundColor(isError: Boolean): Color {
    return when {
        isError -> AppTheme.palette.element.errorTransparent50
        else -> AppTheme.palette.background.primaryGrey
    }
}