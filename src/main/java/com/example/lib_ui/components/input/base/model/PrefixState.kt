package com.example.lib_ui.components.input.base.model

import androidx.compose.ui.unit.Dp

/** @param animatedPlaceholderOffsetX перед номером телефона добавляется +7,
 * поэтому плейсхолдер при анимации должен сдвигаться на кастомный оффсет
 */
internal data class PrefixState(
    val text: String,
    val animatedPlaceholderOffsetX: Dp,
)