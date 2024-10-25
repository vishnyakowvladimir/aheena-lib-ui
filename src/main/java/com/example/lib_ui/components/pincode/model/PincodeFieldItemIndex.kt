package com.example.lib_ui.components.pincode.model

enum class PinCodeFieldItemIndex(val index: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    NONE(-1),
    ;

    fun increase(): PinCodeFieldItemIndex {
        return when (this) {
            NONE -> ZERO
            ZERO -> ONE
            ONE -> TWO
            TWO -> THREE
            THREE -> THREE
        }
    }

    fun decrease(): PinCodeFieldItemIndex {
        return when (this) {
            NONE -> NONE
            ZERO -> NONE
            ONE -> ZERO
            TWO -> ONE
            THREE -> TWO
        }
    }

    companion object {
        fun getPinCodeFieldItemIndex(index: Int): PinCodeFieldItemIndex {
            return when (index) {
                NONE.index -> NONE
                ZERO.index -> ZERO
                ONE.index -> ONE
                TWO.index -> TWO
                THREE.index -> THREE
                else -> NONE
            }
        }
    }
}
