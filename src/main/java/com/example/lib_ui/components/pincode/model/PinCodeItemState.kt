package com.example.lib_ui.components.pincode.model

internal sealed interface PinCodeItemState {

    data class Default(
        val isBefore: Boolean,
        val isCurrent: Boolean,
    ) : PinCodeItemState

    data object Error : PinCodeItemState

    data object Loading : PinCodeItemState
}
