package com.horbal.common.image.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.horbal.common.image.data.PagingConfig.PAGE_SIZE
import com.horbal.common.image.domain.Image
import com.horbal.common.image.domain.ImageRepository

class ImageRepositoryImpl(
    private val remotePagingSourceFactory: () -> PagingSource<Int, Image>,
    private val remoteDetailsSource: ImageDetailsSource,
) : ImageRepository {

    override fun loadImages() = Pager<Int, Image>(
        config = PagingConfig(PAGE_SIZE),
        pagingSourceFactory = remotePagingSourceFactory,
    ).flow

    override suspend fun loadImageDetails(imageId: String) = remoteDetailsSource.load(imageId)
}