package com.hari.kotlintutorial.api

import com.hari.kotlintutorial.models.Movies
import com.hari.kotlintutorial.network.ApiKeyInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * The Movie Data Base Api interface.
 *
 * @author Hari Hara Sudhan.N
 */
interface MovieApi {

    @GET("/3/discover/movie")
    fun getMovies(@QueryMap map: Map<String, String>): Observable<Movies>

    companion object {
        fun create(): MovieApi {
            val okHttpClient = OkHttpClient().newBuilder().addInterceptor(ApiKeyInterceptor()).build()
            val retrofit = Retrofit.Builder().baseUrl("http://api.themoviedb.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            return retrofit.create(MovieApi::class.java)
        }
    }
}