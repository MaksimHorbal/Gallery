package com.horbal.feature.image.details

import com.horbal.common.image.domain.ImageDetails

sealed interface ImageDetailsState {

    data object Loading : ImageDetailsState

    data class Loaded(val details: ImageDetails) : ImageDetailsState

    data object Failure : ImageDetailsState
}