package com.bayu.moviepaging.core.data.source.remote.api.response


import com.google.gson.annotations.SerializedName

data class KeywordResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null
)