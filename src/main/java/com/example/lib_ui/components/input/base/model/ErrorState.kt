package com.example.lib_ui.components.input.base.model

import androidx.compose.runtime.Immutable

@Immutable
abstract class InputErrorState(
    open val textError: String,
    open val isError: Boolean,
)