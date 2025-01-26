package com.horbal.common.image.data.retrofit

import com.horbal.common.image.domain.ImageDetails
import com.horbal.common.image.domain.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDetailsEntity(
    @SerialName("id") val id: String,
    @SerialName("description") val description: String?,
    @SerialName("urls") val urls: UrlsEntity,
    @SerialName("user") val user: UserEntity,
)

fun ImageDetailsEntity.toDomainModel(): ImageDetails = ImageDetails(
    id = id,
    description = description,
    url = urls.regular,
    user = User(
        id = user.id,
        name = user.name,
    )
)