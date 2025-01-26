package com.horbal.common.image.domain

data class Image(
    val id: String,
    val url: String,
    val blurHash: String,
    val width: Int,
    val height: Int,
)