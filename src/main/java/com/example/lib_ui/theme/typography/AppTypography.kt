package com.example.lib_ui.theme.typography

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.unit.sp
import com.example.lib_ui.R

data class AppTypography(
    private val viewScale: ViewScale = ViewScale.M,

    val title1Bold: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 600),
        fontSize = 28.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val title2Bold: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 700),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val title3Bold: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 600),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val title2Medium: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 500),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val text1Regular: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 400),
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val text2Regular: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 400),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val text3Regular: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 400),
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val text2Semibold: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 500),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),

    val button1Regular: TextStyle = AppTextStyle(
        fontFamily = getRobotoFlexFontFamily(fontWeight = 500),
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ).multiplyText(viewScale),
)

@OptIn(ExperimentalTextApi::class)
private fun getRobotoFlexFontFamily(fontWeight: Int): FontFamily {
    return FontFamily(
        Font(
            R.font.roboto_flex_variable,
            variationSettings = FontVariation.Settings(FontVariation.weight(fontWeight)),
        ),
    )
}

private fun AppTextStyle.multiplyText(vs: ViewScale): TextStyle {
    return this.toTextStyle().copy(
        fontSize = this.fontSize * vs.textMultiplier,
        lineHeight = this.lineHeight * vs.textMultiplier,
    )
}


private fun AppTextStyle.toTextStyle(): TextStyle {
    return TextStyle(
        color = this.color,
        fontSize = this.fontSize,
        fontWeight = this.fontWeight,
        fontStyle = this.fontStyle,
        fontSynthesis = this.fontSynthesis,
        fontFamily = this.fontFamily,
        fontFeatureSettings = this.fontFeatureSettings,
        letterSpacing = this.letterSpacing,
        baselineShift = this.baselineShift,
        textGeometricTransform = this.textGeometricTransform,
        localeList = this.localeList,
        background = this.background,
        textDecoration = this.textDecoration,
        shadow = this.shadow,
        textAlign = this.textAlign,
        textDirection = this.textDirection,
        lineHeight = this.lineHeight,
        textIndent = this.textIndent,
        platformStyle = this.platformStyle,
        lineHeightStyle = this.lineHeightStyle,
    )
}

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }