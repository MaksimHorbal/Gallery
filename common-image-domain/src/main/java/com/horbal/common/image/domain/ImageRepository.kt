package com.horbal.common.image.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    fun loadImages(): Flow<PagingData<Image>>

    suspend fun loadImageDetails(imageId: String): ImageDetails
}