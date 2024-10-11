package com.example.lib_ui.components.image

import androidx.compose.foundation.Image as ComposeImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import com.example.lib_ui.components.image.IconSource
import com.example.lib_ui.components.image.toPainter

@Composable
fun ImageFromSource(
    iconSource: IconSource,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    contentDescription: String? = iconSource.contentDescription,
) = ComposeImage(
    painter = iconSource.toPainter(),
    contentDescription = contentDescription,
    modifier = modifier,
    alignment = alignment,
    contentScale = contentScale,
    alpha = alpha,
    colorFilter = colorFilter,
)