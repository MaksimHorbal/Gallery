package com.horbal.common.image.data.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.horbal.common.image.data.PagingConfig.FIRST_PAGE_INDEX
import com.horbal.common.image.data.PagingConfig.PAGE_SIZE
import com.horbal.common.image.domain.Image

class ImagePagingSourceImpl(private val api: ImageApi) : PagingSource<Int, Image>() {

    override suspend fun load(params: LoadParams<Int>) = try {
        val page = params.key ?: FIRST_PAGE_INDEX
        val data = api.loadImages(page, PAGE_SIZE)
            .map(ImageEntity::toDomainModel)
        val prevKey = (page - 1).takeIf { it >= FIRST_PAGE_INDEX }
        val nextKey = if (data.isNotEmpty()) page + 1 else null
        LoadResult.Page(data, prevKey, nextKey)
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Image>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}