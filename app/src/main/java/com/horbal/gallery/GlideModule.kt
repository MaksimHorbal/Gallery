package com.horbal.gallery

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.horbal.common.blurhash.BlurHash
import com.horbal.common.glide.BlurHashModelLoaderFactory

@GlideModule
class GlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.append(
            BlurHash::class.java,
            Bitmap::class.java,
            BlurHashModelLoaderFactory()
        )
    }
}