package com.horbal.common.blurhash

data class BlurHash(
    val value: String,
    val width: Int,
    val height: Int,
) {

    val length = value.length
}