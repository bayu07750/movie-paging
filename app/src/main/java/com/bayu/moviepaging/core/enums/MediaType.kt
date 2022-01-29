package com.bayu.moviepaging.core.enums

enum class MediaType(val type: String) {
    ALL("All"),
    MOVIE("Movie"),
    TV("Tv");

    companion object {
        val DEFAULT = ALL
    }
}