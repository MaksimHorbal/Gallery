package com.horbal.common.image.domain

data class ImageDetails(
    val id: String,
    val description: String?,
    val url: String,
    val user: User,
)