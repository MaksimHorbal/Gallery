package com.horbal.common.glide

import android.graphics.Bitmap
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import com.horbal.common.blurhash.BlurHash
import com.horbal.common.blurhash.BlurHashDecoder

class BlurHashDataFetcher(private val model: BlurHash) : DataFetcher<Bitmap> {

    override fun loadData(
        priority: Priority,
        callback: DataFetcher.DataCallback<in Bitmap>,
    ) {
        try {
            val bitmap = BlurHashDecoder.decode(model)
            if (bitmap != null) {
                callback.onDataReady(bitmap)
            } else {
                callback.onLoadFailed(Exception("Failed to decode BlurHash"))
            }
        } catch (e: Exception) {
            callback.onLoadFailed(e)
        }
    }

    override fun cleanup() = Unit

    override fun cancel() = Unit

    override fun getDataClass() = Bitmap::class.java

    override fun getDataSource() = DataSource.LOCAL
}