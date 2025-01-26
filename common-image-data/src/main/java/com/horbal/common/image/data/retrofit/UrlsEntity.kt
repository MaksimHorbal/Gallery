package com.horbal.common.image.data.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UrlsEntity(
    @SerialName("small") val small: String,
    @SerialName("regular") val regular: String,
)