package com.horbal.common.glide

import android.graphics.Bitmap
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.horbal.common.blurhash.BlurHash

class BlurHashModelLoaderFactory : ModelLoaderFactory<BlurHash, Bitmap> {

    override fun build(multiFactory: MultiModelLoaderFactory) = BlurHashModelLoader()

    override fun teardown() = Unit
}