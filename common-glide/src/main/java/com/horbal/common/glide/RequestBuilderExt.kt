package com.horbal.common.glide

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

inline fun <T> RequestBuilder<T>.onResourceReady(crossinline block: (T) -> Unit): RequestBuilder<T> {
    return addListener(object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<T?>,
            isFirstResource: Boolean,
        ) = false

        override fun onResourceReady(
            resource: T & Any,
            model: Any,
            target: Target<T?>?,
            dataSource: DataSource,
            isFirstResource: Boolean,
        ): Boolean {
            block(resource)
            return false
        }
    })
}

inline fun <T> RequestBuilder<T>.onLoadFailed(crossinline block: (GlideException?) -> Unit): RequestBuilder<T> {
    return addListener(object : RequestListener<T> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<T?>,
            isFirstResource: Boolean,
        ): Boolean {
            block(e)
            return false
        }

        override fun onResourceReady(
            resource: T & Any,
            model: Any,
            target: Target<T?>?,
            dataSource: DataSource,
            isFirstResource: Boolean,
        ) = false
    })
}