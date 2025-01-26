package com.horbal.common.image.data.retrofit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
)