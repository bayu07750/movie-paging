package com.bayu.moviepaging.core.datamappers

import com.bayu.moviepaging.core.data.source.remote.api.response.MediaResponse
import com.bayu.moviepaging.domain.model.Media

object MediaDataMapper {

    fun MediaResponse.mapResponseToDomain(): Media {
        return Media(
            id = id ?: 0,
            name = name.orEmpty(),
            title = title.orEmpty(),
            rating = voteAverage ?: 0.0,
            posterPath = posterPath.orEmpty()
        )
    }

    fun List<MediaResponse>.mapResponseToDomain(): List<Media> {
        return map {
            it.mapResponseToDomain()
        }
    }

}