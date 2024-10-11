package com.example.lib_ui.components.input.amount_input

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.lib_ui.components.input.amount_input.view.AmountDecoratedInputContainer
import com.example.lib_ui.components.input.utils.AmountVisualTransformation
import com.example.lib_ui.theme.AppTheme

private const val RUB_SYMBOL = "\u20BD"

private const val INPUT_AMOUNT_SHORT_LENGTH = 6
private const val INPUT_AMOUNT_MIDDLE_LENGTH = 8
private val INPUT_AMOUNT_SHORT_TEXT_SIZE = 24.sp
private val INPUT_AMOUNT_MIDDLE_TEXT_SIZE = 22.sp
private val INPUT_AMOUNT_LONG_TEXT_SIZE = 20.sp

@Composable
fun AmountInput(
    state: AmountInputState,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalAmountInputTextTypography provides AppTheme.typography.title2Bold,
        LocalAmountInputPlaceholderColor provides AppTheme.palette.background.transparent50,
        LocalAmountInputCurrencySymbol provides RUB_SYMBOL,
    ) {
        val textLength = state.mainState.textFieldValue.text.length
        val fontSize = when {
            textLength <= INPUT_AMOUNT_SHORT_LENGTH -> INPUT_AMOUNT_SHORT_TEXT_SIZE
            textLength <= INPUT_AMOUNT_MIDDLE_LENGTH -> INPUT_AMOUNT_MIDDLE_TEXT_SIZE
            else -> INPUT_AMOUNT_LONG_TEXT_SIZE
        }

        BasicTextField(
            modifier = modifier,
            value = state.mainState.textFieldValue,
            textStyle = LocalAmountInputTextTypography.current.copy(
                fontSize = fontSize,
                color = AppTheme.palette.text.primary,
            ),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(AppTheme.palette.element.accent),
            keyboardActions = state.mainState.keyboardActions,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = state.mainState.imeAction,
            ),
            visualTransformation = AmountVisualTransformation(
                currency = LocalAmountInputCurrencySymbol.current,
                currencyColor = LocalAmountInputPlaceholderColor.current,
            ),
            onValueChange = { field ->
                state.mainState.onValueChange(field)
            },
            decorationBox = { innerTextField ->
                AmountDecoratedInputContainer(
                    textFieldValue = state.mainState.textFieldValue,
                    title = state.mainState.title,
                    innerTextField = innerTextField,
                    onResetIconClick = state.tailState.onResetIconClick,
                    errorState = state.errorState,
                )
            },
        )
    }
}

internal val LocalAmountInputTextTypography = compositionLocalOf<TextStyle> {
    error("LocalAmountInputTextTypography not present")
}

internal val LocalAmountInputPlaceholderColor = compositionLocalOf<Color> {
    error("LocalAmountInputPlaceholderColor not present")
}

internal val LocalAmountInputCurrencySymbol = compositionLocalOf<String> {
    error("LocalAmountInputCurrencySymbol not present")
}