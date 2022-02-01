package com.bayu.moviepaging.core.data.source.remote.api

import com.bayu.moviepaging.BuildConfig
import com.bayu.moviepaging.core.data.source.remote.api.response.KeywordsResponse
import com.bayu.moviepaging.core.data.source.remote.api.response.MediasResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/{type}/{time}")
    suspend fun getTrending(
        @Path("type") type: String,
        @Path("time") time: String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int,
    ): MediasResponse

    @GET("search/keyword")
    suspend fun getKeywordsSearch(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("page") page: Int = 1,
        @Query("query") query: String,
    ): KeywordsResponse

}