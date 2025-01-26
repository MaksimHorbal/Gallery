package com.horbal.common.image.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageApi {

    @GET("photos?")
    suspend fun loadImages(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): List<ImageEntity>

    @GET("photos/{id}")
    suspend fun loadImageDetails(@Path("id") id: String): ImageDetailsEntity
}