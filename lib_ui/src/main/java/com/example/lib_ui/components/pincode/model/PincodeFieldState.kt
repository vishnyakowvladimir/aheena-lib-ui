package com.example.lib_ui.components.pincode.model

data class PinCodeFieldState(
    val title: String,
    val maxIndex: PinCodeFieldItemIndex,
    val type: PinCodeFieldType,
) {

}

sealed interface PinCodeFieldType {

    data class Default(val selectedIndex: PinCodeFieldItemIndex) : PinCodeFieldType

    data class Error(val errorText: String) : PinCodeFieldType

    data object Loading : PinCodeFieldType
}
