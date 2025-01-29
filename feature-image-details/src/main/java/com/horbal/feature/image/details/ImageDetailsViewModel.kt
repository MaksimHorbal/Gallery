package com.horbal.feature.image.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horbal.common.image.domain.ImageRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ImageDetailsViewModel.Factory::class)
class ImageDetailsViewModel @AssistedInject constructor(
    private val repository: ImageRepository,
    @Assisted private val imageId: String,
) : ViewModel() {

    private val _state = MutableStateFlow<ImageDetailsState>(ImageDetailsState.Loading)
    val state: Flow<ImageDetailsState>
        get() = _state.asSharedFlow()

    init {
        viewModelScope.launch {
            runCatching { repository.loadImageDetails(imageId) }
                .onSuccess { _state.value = ImageDetailsState.Loaded(it) }
                .onFailure { _state.value = ImageDetailsState.Failure }
        }
    }

    @AssistedFactory
    fun interface Factory {

        fun create(imageId: String): ImageDetailsViewModel
    }
}