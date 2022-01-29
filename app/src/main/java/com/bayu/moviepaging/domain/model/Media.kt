package com.bayu.moviepaging.domain.model

data class Media(
    val id: Int,
    val name: String,
    val title: String,
    val rating: Double,
    val posterPath: String
) {

    fun parsePoster(): String = "https://image.tmdb.org/t/p/w500${posterPath}"

}