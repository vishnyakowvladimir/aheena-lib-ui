package com.example.lib_ui.components.image

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.lib_ui.components.image.IconSource

internal object ImageRequestFactory {

    /**
     * Фабрика для создания ImageRequest если урл CDN
     * специально игнорирует кеширование потому что кеш работает на уровне Okhttp
     */
    inline fun createImageRequest(
        context: Context,
        url: IconSource.FromUrl,
        config: ImageRequest.Builder.() -> Unit = {},
    ): ImageRequest {
        val builder = ImageRequest.Builder(context)
        config.invoke(builder)
        builder.data(url.url)
        return if (url.isCdn) {
            builder
                .diskCachePolicy(CachePolicy.DISABLED)
                .memoryCachePolicy(CachePolicy.DISABLED)
                .build()
        } else {
            builder
                .build()
        }
    }
}