package com.bayu.moviepaging.core.data.source.remote.api.response


import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int>? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("media_type")
    val mediaType: String? = null,
    @SerializedName("first_air_date")
    val firstAirDate: String? = null,
    @SerializedName("origin_country")
    val originCountry: List<String>? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null
)