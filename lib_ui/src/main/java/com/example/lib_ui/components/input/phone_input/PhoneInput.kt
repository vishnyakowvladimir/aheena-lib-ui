package com.example.lib_ui.components.input.phone_input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.input.base.BaseInput
import com.example.lib_ui.components.input.base.model.PrefixState
import com.example.lib_ui.components.input.utils.MaskVisualTransformation

private const val MAX_LENGTH = 10
private const val PHONE_NUMBER_MASK = "###-###-##-##"
private const val PHONE_NUMBER_PREFIX = "+7 "

/* перед номером телефона добавляется +7, поэтому плейсхолдер при анимации должен сдвигаться
* на кастомный оффсет
*/
private val animatedPlaceholderOffsetX = (-20).dp

@Composable
fun PhoneInput(
    state: PhoneInputState,
    modifier: Modifier = Modifier,

    ) {
    BaseInput(
        modifier = modifier,
        textFieldValue = state.mainState.textFieldValue,
        title = state.mainState.title,
        placeholderText = state.mainState.placeholderText,
        onValueChange = state.mainState.onValueChange,
        maxLength = MAX_LENGTH,
        onResetIconClick = state.tailState.onResetIconClick,
        iconRes = null,
        onIconClick = {},
        errorState = state.errorState,
        keyboardActions = state.mainState.keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = state.mainState.imeAction,
        ),
        visualTransformation = MaskVisualTransformation(PHONE_NUMBER_MASK),
        prefixState = PrefixState(
            text = PHONE_NUMBER_PREFIX,
            animatedPlaceholderOffsetX = animatedPlaceholderOffsetX,
        ),
    )
}