package com.hari.kotlintutorial.network

import com.hari.kotlintutorial.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Hari Hara Sudhan.N
 */
internal class ApiKeyInterceptor : Interceptor {
    override fun intercept(requestObject: Interceptor.Chain): Response {
        val request = requestObject.request()
        val requestUrl = request.url()
        val actualUrl = requestUrl
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.MOVIE_DB_API_KEY)
                .build()
        val requestBuilder = request.newBuilder().url(actualUrl)
        return requestObject.proceed(requestBuilder.build())
    }
}