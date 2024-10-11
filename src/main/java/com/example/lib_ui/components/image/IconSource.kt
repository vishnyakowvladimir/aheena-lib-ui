package com.example.lib_ui.components.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

private const val VECTOR_IMAGE_FILE_EXTENSION = ".xml"

sealed class IconSource {

    abstract val contentDescription: String?

    abstract val backgroundColor: Color?

    abstract val shape: Shape

    abstract val size: Dp?

    abstract val tint: Color?

    data class FromResource(
        val resourceId: Int,
        override val contentDescription: String? = null,
        override val backgroundColor: Color? = null,
        override val shape: Shape = RectangleShape,
        override val size: Dp? = null,
        override val tint: Color? = null,
    ) : IconSource() {

        @Composable
        fun isVector(): Boolean {
            val context = LocalContext.current
            val res = context.resources
            val value = remember { TypedValue() }
            res.getValue(resourceId, value, true)
            val path = value.string
            // Assume .xml suffix implies a vector resource
            return path?.endsWith(VECTOR_IMAGE_FILE_EXTENSION) == true
        }
    }

    data class FromBitmap(
        val bitmap: Bitmap,
        override val contentDescription: String?,
        override val backgroundColor: Color? = null,
        override val shape: Shape = RectangleShape,
        override val size: Dp? = null,
        override val tint: Color? = null,
    ) : IconSource()

    data class FromDrawable(
        val drawable: Drawable,
        override val contentDescription: String?,
        override val backgroundColor: Color? = null,
        override val shape: Shape = RectangleShape,
        override val size: Dp? = null,
        override val tint: Color? = null,
    ) : IconSource()

    data class FromUrl(
        val url: String,
        override val contentDescription: String?,
        @DrawableRes val placeholderRes: Int? = null,
        @DrawableRes val errorRes: Int? = null,
        override val backgroundColor: Color? = null,
        override val shape: Shape = RectangleShape,
        override val size: Dp? = null,
        override val tint: Color? = null,
        val onState: ((AsyncImagePainter.State) -> Unit)? = null,
        val isCdn: Boolean = false,
    ) : IconSource()

    data class FromUri(
        val uri: Uri,
        override val contentDescription: String?,
        override val backgroundColor: Color? = null,
        override val shape: Shape = RectangleShape,
        override val size: Dp? = null,
        override val tint: Color? = null,
    ) : IconSource()
}

@Composable
fun IconSource.toPainter() = when (this) {
    is IconSource.FromResource -> painterResource(id = this.resourceId)
    is IconSource.FromBitmap -> rememberAsyncImagePainter(model = bitmap)
    is IconSource.FromDrawable -> rememberAsyncImagePainter(model = drawable)
    is IconSource.FromUri -> rememberAsyncImagePainter(model = this.uri)
    is IconSource.FromUrl -> rememberAsyncImagePainter(
        model = ImageRequestFactory.createImageRequest(
            context = LocalContext.current,
            url = this,
            config = {
                placeholderRes?.let(::placeholder)
                errorRes?.let(::error)
            }
        ),
        onState = onState
    )
}