package com.horbal.common.android.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Authorization", token)
            .build()
        return chain.proceed(request)
    }
}