package com.bayu.moviepaging.core.data.source.remote.api.response


import com.google.gson.annotations.SerializedName

data class KeywordsResponse(
    @SerializedName("results")
    val results: List<KeywordResponse>? = null,
)