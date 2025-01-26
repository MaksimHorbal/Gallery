package com.horbal.common.image.data

import com.horbal.common.image.domain.ImageDetails

fun interface ImageDetailsSource {

    suspend fun load(imageId: String): ImageDetails
}