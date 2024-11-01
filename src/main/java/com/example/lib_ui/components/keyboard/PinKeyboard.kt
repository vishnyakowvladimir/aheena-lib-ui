package com.example.lib_ui.components.keyboard

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.ImageFromSource
import com.example.lib_ui.components.keyboard.model.PinKey
import com.example.lib_ui.components.keyboard.model.PinKeyType
import com.example.lib_ui.theme.AppTheme

@Composable
fun PinKeyboard(
    modifier: Modifier = Modifier,
    withBiometrics: Boolean = false,
    onClick: (PinKey) -> Unit,
) {

    val keys1 by remember {
        mutableStateOf(
            listOf(
                PinKey.ONE,
                PinKey.TWO,
                PinKey.THREE,
            )
        )
    }

    val keys2 by remember {
        mutableStateOf(
            listOf(
                PinKey.FOUR,
                PinKey.FIVE,
                PinKey.SIX,
            )
        )
    }

    val keys3 by remember {
        mutableStateOf(
            listOf(
                PinKey.SEVEN,
                PinKey.EIGHT,
                PinKey.NINE,
            )
        )
    }

    val keys4 by remember {
        mutableStateOf(
            listOf(
                if (withBiometrics) PinKey.BIOMETRICS else PinKey.EMPTY,
                PinKey.ZERO,
                PinKey.DELETE,
            )
        )
    }

    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        Row {
            keys1.forEach { key ->
                KeyBlock(
                    key = key,
                    modifier = Modifier.weight(1f),
                    onClick = onClick,
                )
            }
        }

        SpacerVertical()

        Row {
            keys2.forEach { key ->
                KeyBlock(
                    key = key,
                    modifier = Modifier.weight(1f),
                    onClick = onClick,
                )
            }
        }

        SpacerVertical()

        Row {
            keys3.forEach { key ->
                KeyBlock(
                    key = key,
                    modifier = Modifier.weight(1f),
                    onClick = onClick,
                )
            }
        }

        SpacerVertical()

        Row {
            keys4.forEach { key ->
                KeyBlock(
                    key = key,
                    modifier = Modifier.weight(1f),
                    onClick = onClick,
                )
            }
        }
    }
}

@Composable
private fun KeyBlock(
    modifier: Modifier = Modifier,
    key: PinKey,
    onClick: (PinKey) -> Unit,
) {
    CompositionLocalProvider(
        LocalKeyboardIndication provides ripple(
            color = AppTheme.palette.background.transparent8,
            radius = 32.dp,
            bounded = false,
        )
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center,
        ) {
            when (key.type) {
                is PinKeyType.Digit -> {
                    Text(
                        text = "${key.type.value}",
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = LocalKeyboardIndication.current,
                            onClick = { onClick(key) },
                        ),
                        color = AppTheme.palette.text.primary,
                        style = AppTheme.typography.title1Bold,
                    )
                }

                is PinKeyType.Icon -> {
                    ImageFromSource(
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = LocalKeyboardIndication.current,
                            onClick = { onClick(key) },
                        ),
                        iconSource = IconSource.FromResource(
                            resourceId = key.type.iconRes,
                        ),
                        colorFilter = ColorFilter.tint(color = AppTheme.palette.text.primary),
                    )
                }

                is PinKeyType.Empty -> Unit
            }
        }
    }

}

@Composable
private fun SpacerVertical() {
    Spacer(modifier = Modifier.height(20.dp))
}

private val LocalKeyboardIndication = compositionLocalOf<Indication> {
    error("LocalKeyboardIndication not present")
}
