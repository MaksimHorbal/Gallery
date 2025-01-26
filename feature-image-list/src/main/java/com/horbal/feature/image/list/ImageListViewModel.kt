package com.horbal.feature.image.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.horbal.common.image.domain.Image
import com.horbal.common.image.domain.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    repository: ImageRepository,
    private val onImageClick: OnImageClickCallback,
) : ViewModel() {

    fun interface OnImageClickCallback {

        operator fun invoke(imageId: String)
    }

    val images: Flow<PagingData<Image>> = repository.loadImages()
        .cachedIn(viewModelScope)

    fun imageClicked(imageId: String) {
        onImageClick(imageId)
    }
}