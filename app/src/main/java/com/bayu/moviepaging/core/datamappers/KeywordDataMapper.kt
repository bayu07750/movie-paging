package com.bayu.moviepaging.core.datamappers

import com.bayu.moviepaging.core.data.source.remote.api.response.KeywordResponse
import com.bayu.moviepaging.domain.model.Keyword

object KeywordDataMapper {

    fun KeywordResponse.mapResponseToDomain(): Keyword {
        return Keyword(id ?: 0, name.orEmpty())
    }

    fun List<KeywordResponse>.mapResponseToDomain(): List<Keyword> {
        return map { it.mapResponseToDomain() }
    }

}