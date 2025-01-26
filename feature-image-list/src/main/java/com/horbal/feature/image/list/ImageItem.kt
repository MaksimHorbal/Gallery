package com.horbal.feature.image.list

import com.horbal.common.blurhash.BlurHash
import com.horbal.common.image.domain.Image

data class ImageItem(
    val id: String,
    val url: String,
    val blurHash: BlurHash,
)

fun Image.toUiModel(blurHashScaleFactor: Float = 0.25f): ImageItem = ImageItem(
    id = id,
    url = url,
    blurHash = BlurHash(
        value = blurHash,
        width = width.times(blurHashScaleFactor).toInt(),
        height = height.times(blurHashScaleFactor).toInt(),
    )
)