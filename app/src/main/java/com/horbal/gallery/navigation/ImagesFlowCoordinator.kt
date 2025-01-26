package com.horbal.gallery.navigation

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImagesFlowCoordinator @Inject constructor(private val navigator: Navigator) {

    fun start() {
        navigator.showImageList()
    }

    fun seeImageDetails(imageId: String) {
        navigator.showImageDetails(imageId)
    }
}