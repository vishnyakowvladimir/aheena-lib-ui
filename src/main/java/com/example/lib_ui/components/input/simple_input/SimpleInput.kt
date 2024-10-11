package com.example.lib_ui.components.input.simple_input

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lib_ui.components.input.base.BaseInput

@Composable
fun SimpleInput(
    state: SimpleInputState,
    modifier: Modifier = Modifier,
) {

    BaseInput(
        modifier = modifier,
        textFieldValue = state.mainState.textFieldValue,
        title = state.mainState.title,
        placeholderText = state.mainState.placeholderText,
        onValueChange = state.mainState.onValueChange,
        maxLength = state.mainState.maxLength,
        keyboardActions = state.mainState.keyboardActions,
        keyboardOptions = state.mainState.keyboardOptions,
        visualTransformation = state.mainState.visualTransformation,
        onResetIconClick = state.tailState.onResetIconClick,
        iconRes = state.tailState.iconRes,
        onIconClick = state.tailState.onIconClick,
        errorState = state.errorState,
    )
}

