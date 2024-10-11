package com.example.lib_ui.components.input.password_input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.lib_ui.R
import com.example.lib_ui.components.input.base.BaseInput

@Composable
fun PasswordInput(
    state: PasswordInputState,
    modifier: Modifier = Modifier,
) {

    val visualTransformation = if (state.mainState.isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    val iconRes = if (state.mainState.isPasswordVisible) {
        R.drawable.ic_24dp_actions_hide
    } else {
        R.drawable.ic_24dp_actions_show
    }

    BaseInput(
        modifier = modifier,
        textFieldValue = state.mainState.textFieldValue,
        title = state.mainState.title,
        placeholderText = state.mainState.placeholderText,
        onValueChange = state.mainState.onValueChange,
        maxLength = state.mainState.maxLength,
        keyboardActions = state.mainState.keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = state.mainState.keyboardImeAction,
        ),
        visualTransformation = visualTransformation,
        onResetIconClick = state.tailState.onResetIconClick,
        iconRes = iconRes,
        onIconClick = state.tailState.onIconClick,
        errorState = state.errorState,
    )
}

