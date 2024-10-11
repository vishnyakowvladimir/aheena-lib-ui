package com.example.lib_ui.components.input.simple_input

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.lib_ui.components.input.base.model.InputErrorState


data class SimpleInputState(
    val mainState: MainState,
    val tailState: TailState,
    val errorState: ErrorState = ErrorState(
        textError = "",
        isError = false,
    ),
) {

    data class MainState(
        val textFieldValue: TextFieldValue,
        val placeholderText: String,
        val onValueChange: (TextFieldValue) -> Unit,
        val title: String = "",
        val maxLength: Int = Int.MAX_VALUE,
        val keyboardActions: KeyboardActions = KeyboardActions.Default,
        val keyboardOptions: KeyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        val visualTransformation: VisualTransformation = VisualTransformation.None,
    )

    data class TailState(
        val iconRes: Int? = null,
        val onIconClick: () -> Unit = {},
        val onResetIconClick: () -> Unit = {},
    )

    /* errorState не должен быть null из-за анимации. Иначе стирается текст, и анимацию не видно.
    * если была ошибка и потом надо ее скрыть, то текст ошибки сохраняем, а меняем только isError = false.
    * иначе анимируется пустая строка и анимацию не видно
    */
    data class ErrorState(
        override val textError: String,
        override val isError: Boolean = false,
    ): InputErrorState(
        textError = textError,
        isError = isError,
    )
}