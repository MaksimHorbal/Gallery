package com.horbal.common.image.data.retrofit

import com.horbal.common.image.domain.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageEntity(
    @SerialName("id") val id: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("blur_hash") val blurHash: String,
    @SerialName("urls") val urls: UrlsEntity,
)

fun ImageEntity.toDomainModel(): Image = Image(
    id = id,
    url = urls.small,
    blurHash = blurHash,
    width = width,
    height = height,
)