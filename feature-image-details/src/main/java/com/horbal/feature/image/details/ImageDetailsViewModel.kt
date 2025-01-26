package com.horbal.feature.image.details

import androidx.lifecycle.ViewModel
import com.horbal.common.image.domain.ImageRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@HiltViewModel(assistedFactory = ImageDetailsViewModel.Factory::class)
class ImageDetailsViewModel @AssistedInject constructor(
    private val repository: ImageRepository,
    @Assisted private val imageId: String,
) : ViewModel() {

    val state: Flow<ImageDetailsState> = flow {
        emit(ImageDetailsState.Loading)
        runCatching { repository.loadImageDetails(imageId) }
            .onSuccess { emit(ImageDetailsState.Loaded(it)) }
            .onFailure { emit(ImageDetailsState.Failure(it)) }
    }

    @AssistedFactory
    fun interface Factory {

        fun create(imageId: String): ImageDetailsViewModel
    }
}