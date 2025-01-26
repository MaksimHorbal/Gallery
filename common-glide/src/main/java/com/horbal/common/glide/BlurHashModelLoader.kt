package com.horbal.common.glide

import android.graphics.Bitmap
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey
import com.horbal.common.blurhash.BlurHash

class BlurHashModelLoader : ModelLoader<BlurHash, Bitmap> {

    override fun buildLoadData(
        model: BlurHash,
        width: Int,
        height: Int,
        options: Options,
    ) = ModelLoader.LoadData(ObjectKey(model), BlurHashDataFetcher(model))

    override fun handles(model: BlurHash) = true
}