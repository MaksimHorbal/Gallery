package com.horbal.feature.image.details

import com.horbal.common.image.domain.ImageDetails

sealed interface ImageDetailsState {

    object Loading : ImageDetailsState

    class Loaded(val details: ImageDetails) : ImageDetailsState

    class Failure(val error: Throwable) : ImageDetailsState
}