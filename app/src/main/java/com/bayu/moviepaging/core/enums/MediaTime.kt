package com.bayu.moviepaging.core.enums

enum class MediaTime(val time: String) {
    DAY("day"),
    WEEK("week");

    companion object {
        val DEFAULT = DAY
    }
}