package com.horbal.common.image.wiring

import androidx.paging.PagingSource
import com.horbal.common.android.network.AuthTokenInterceptor
import com.horbal.common.android.network.MediaTypeInterceptor
import com.horbal.common.image.data.BuildConfig
import com.horbal.common.image.data.ImageDetailsSource
import com.horbal.common.image.data.ImageRepositoryImpl
import com.horbal.common.image.data.retrofit.ImageApi
import com.horbal.common.image.data.retrofit.ImageDetailsSourceImpl
import com.horbal.common.image.data.retrofit.ImagePagingSourceImpl
import com.horbal.common.image.domain.Image
import com.horbal.common.image.domain.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Provider

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {

    @Provides
    fun provideImageRepository(
        remotePagingSource: Provider<PagingSource<Int, Image>>,
        remoteDetailsSource: ImageDetailsSource,
    ): ImageRepository = ImageRepositoryImpl(
        remotePagingSourceFactory = { remotePagingSource.get() },
        remoteDetailsSource = remoteDetailsSource,
    )

    @Provides
    fun provideRemotePagingSource(api: ImageApi): PagingSource<Int, Image> =
        ImagePagingSourceImpl(api)

    @Provides
    fun provideRemoteDetailsSource(api: ImageApi): ImageDetailsSource =
        ImageDetailsSourceImpl(api)

    @Provides
    fun provideImageApi(retrofit: Retrofit): ImageApi = retrofit.create()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.IMAGE_BASE_URL)
        .addConverterFactory(
            Json { ignoreUnknownKeys = true }.asConverterFactory(
                BuildConfig.IMAGE_MEDIA_TYPE.toMediaType()
            )
        )
        .client(
            client.newBuilder()
                .addInterceptor(AuthTokenInterceptor(BuildConfig.IMAGE_API_KEY))
                .addInterceptor(MediaTypeInterceptor(BuildConfig.IMAGE_MEDIA_TYPE))
                .build()
        )
        .build()
}