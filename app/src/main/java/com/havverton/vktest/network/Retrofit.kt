package com.havverton.vktest.network

import android.content.Context
import com.havverton.vktest.MainActivity
import com.havverton.vktest.models.MainFeedResponse
import com.havverton.vktest.models.Response
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.VKApiConfig
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object RetrofitModule {
    private val json = Json { ignoreUnknownKeys = true }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.vk.com/method/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
    val vkApi: VKApi = retrofit.create()
}

interface VKApi {

    //   @GET("genre/movie/list?api_key=${AppConfig.API_KEY}&language=en-US ${VKApiConfig.DEFAULT_API_VERSION}")
    //  suspend fun getGenreList(): GenrePage
    @GET("users.get?user_id=20496102&access_token=&v=${VKApiConfig.DEFAULT_API_VERSION}")
    suspend fun getUser(@Query("access_token") token:String,  ): Response

    @GET("newsfeed.get?filters=post&count=50&fields=attachments,likes,views,reposts,comments&access_token=&v=${VKApiConfig.DEFAULT_API_VERSION}")
    suspend fun getFeed(@Query("access_token") token:String): MainFeedResponse

}