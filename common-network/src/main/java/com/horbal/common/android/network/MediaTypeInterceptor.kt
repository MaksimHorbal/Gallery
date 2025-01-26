package com.horbal.common.android.network

import okhttp3.Interceptor
import okhttp3.Response

class MediaTypeInterceptor(private val mediaType: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("accept", mediaType)
            .build()
        return chain.proceed(request)
    }
}