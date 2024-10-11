package com.example.lib_ui.components.input.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

private const val AMOUNT_POSTFIX_DIVIDER = " "
private const val LOCALE_LANGUAGE = "ru"
private const val LOCALE_COUNTRY = "RU"
private const val AMOUNT_FORMAT_PATTERN = "#,###"
private const val LOCALIZED_GROUPING_SEPARATOR = ' '

class AmountVisualTransformation(
    currency: String,
    private val currencyColor: Color,
) : VisualTransformation {
    private val amountFormat: DecimalFormat

    private var limitPostfix = AMOUNT_POSTFIX_DIVIDER + currency

    init {
        val locale = Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY)
        val dfSymbols = DecimalFormatSymbols(locale)
        dfSymbols.groupingSeparator = LOCALIZED_GROUPING_SEPARATOR
        amountFormat = DecimalFormat(AMOUNT_FORMAT_PATTERN, dfSymbols)
        amountFormat.roundingMode = RoundingMode.DOWN
    }

    override fun filter(text: AnnotatedString): TransformedText {
        if (text.isEmpty()) return TransformedText(AnnotatedString(""), OffsetMapping.Identity)

        val splitted = text.split(",")
        val first = splitted.getOrNull(0) ?: ""
        val second = splitted.getOrNull(1)
        val sBigDecimal =
            kotlin.runCatching {
                first.replace(",", ".").toBigDecimal()
            }.getOrNull() ?: BigDecimal(0)
        val firstString = amountFormat.format(sBigDecimal)
        val string = firstString + second?.let { ",$second" }.orEmpty()
        val transformedCharList = string.toCharArray().toList()
            .mapIndexed { index, c -> Pair(index, c) }
        var shift = 0
        val transformedCharList2 = transformedCharList
            .map {
                val result = it.first - shift
                if (it.second == ' ') {
                    shift++
                }
                result
            }
        val indexToOrigin = transformedCharList2 + listOf(transformedCharList2.last() + 1)
        val originalCharList = transformedCharList
            .filter { it.second.isDigit() || it.second == ',' }

        val resultTextBuilder = AnnotatedString.Builder()
        resultTextBuilder.append(string)
        resultTextBuilder.withStyle(style = SpanStyle(color = currencyColor)) {
            append(limitPostfix)
        }

        return TransformedText(
            resultTextBuilder.toAnnotatedString(),
            object : OffsetMapping {

                override fun originalToTransformed(offset: Int): Int {
                    if (transformedCharList.getOrNull(0)?.second == '0') {
                        return (offset + 1).coerceIn(0, originalCharList.size)
                    }
                    if (offset == 0) return 0
                    val target = originalCharList[
                        (offset - 1).coerceIn(0, originalCharList.lastIndex),
                    ]
                    return target.first + 1
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset == 0) return 0
                    val target = if (indexToOrigin.isNotEmpty()) {
                        indexToOrigin[offset.coerceIn(0, indexToOrigin.lastIndex)]
                    } else {
                        0
                    }
                    return target.coerceIn(0, text.length)
                }
            },
        )
    }
}