package com.bayu.moviepaging.core.data.source.remote.api

import com.bayu.moviepaging.core.data.source.remote.api.response.MediasResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/{type}/{time}")
    suspend fun getTrending(
        @Path("type") type: String = "all",
        @Path("time") time: String = "day",
        @Query("api_key") apiKey: String = "",
        @Query("page") page: Int,
    ): MediasResponse

}