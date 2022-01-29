package com.bayu.moviepaging.core.data.source.remote.api.response


import com.google.gson.annotations.SerializedName

data class MediasResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MediaResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)