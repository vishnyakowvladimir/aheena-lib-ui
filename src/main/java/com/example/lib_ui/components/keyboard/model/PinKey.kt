package com.example.lib_ui.components.keyboard.model

import com.example.lib_ui.R

enum class PinKey(val type: PinKeyType) {
    ZERO(PinKeyType.Digit(0)),
    ONE(PinKeyType.Digit(1)),
    TWO(PinKeyType.Digit(2)),
    THREE(PinKeyType.Digit(3)),
    FOUR(PinKeyType.Digit(4)),
    FIVE(PinKeyType.Digit(5)),
    SIX(PinKeyType.Digit(6)),
    SEVEN(PinKeyType.Digit(7)),
    EIGHT(PinKeyType.Digit(8)),
    NINE(PinKeyType.Digit(9)),
    DELETE(PinKeyType.Icon(R.drawable.ic_40dp_keyboard_icons_delete)),
    BIOMETRICS(PinKeyType.Icon(R.drawable.ic_40dp_keyboard_icons_fingerprint)),
    EMPTY(PinKeyType.Empty),
    ;
}
