package com.horbal.gallery.navigation

import com.horbal.feature.image.list.ImageListViewModel.OnImageClickCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Provides
    fun provideOnImageClickCallback(coordinator: ImagesFlowCoordinator): OnImageClickCallback =
        OnImageClickCallback { coordinator.seeImageDetails(it) }
}