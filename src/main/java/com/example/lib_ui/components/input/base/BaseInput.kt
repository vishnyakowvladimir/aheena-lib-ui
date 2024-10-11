package com.example.lib_ui.components.input.base

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.input.base.model.InputErrorState
import com.example.lib_ui.components.input.base.model.PrefixState
import com.example.lib_ui.components.input.base.view.DecoratedInputContainer
import com.example.lib_ui.theme.AppTheme

@Composable
internal fun BaseInput(
    textFieldValue: TextFieldValue,
    placeholderText: String,
    onResetIconClick: () -> Unit,
    onValueChange: (TextFieldValue) -> Unit,
    errorState: InputErrorState,
    modifier: Modifier = Modifier,
    title: String = "",
    iconRes: Int? = null,
    onIconClick: () -> Unit = {},
    maxLength: Int = Int.MAX_VALUE,
    prefixState: PrefixState? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
    ),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    CompositionLocalProvider(
        LocalBaseInputHeight provides 68.dp,
        LocalBaseInputTypography provides AppTheme.typography.text1Regular,
        LocalBaseInputColor provides AppTheme.palette.text.primary,
    ) {
        BasicTextField(
            modifier = modifier
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },
            value = textFieldValue,
            textStyle = LocalBaseInputTypography.current.copy(
                color = LocalBaseInputColor.current,
            ),
            singleLine = true,
            maxLines = 1,
            cursorBrush = SolidColor(AppTheme.palette.element.accent),
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            onValueChange = { field ->
                if (field.text.length <= maxLength) {
                    onValueChange(field)
                }
            },
            decorationBox = { innerTextField ->
                DecoratedInputContainer(
                    textFieldValue = textFieldValue,
                    title = title,
                    placeholderText = placeholderText,
                    innerTextField = innerTextField,
                    isFocused = isFocused,
                    iconRes = iconRes,
                    onIconClick = onIconClick,
                    onResetIconClick = onResetIconClick,
                    errorState = errorState,
                    prefixState = prefixState,
                )
            },
        )
    }
}

internal val LocalBaseInputHeight = compositionLocalOf<Dp> {
    error("LocalBaseInputHeight not present")
}

internal val LocalBaseInputColor = compositionLocalOf<Color> {
    error("LocalBaseInputColor not present")
}

internal val LocalBaseInputTypography = compositionLocalOf<TextStyle> {
    error("LocalBaseInputTypography not present")
}