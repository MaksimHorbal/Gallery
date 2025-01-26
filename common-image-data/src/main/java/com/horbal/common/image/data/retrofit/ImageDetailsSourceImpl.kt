package com.horbal.common.image.data.retrofit

import com.horbal.common.image.data.ImageDetailsSource

class ImageDetailsSourceImpl(private val api: ImageApi) : ImageDetailsSource {

    override suspend fun load(imageId: String) = api.loadImageDetails(imageId)
        .toDomainModel()
}