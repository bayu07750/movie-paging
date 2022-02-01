package com.bayu.moviepaging.domain.usecase.media

import androidx.paging.PagingData
import com.bayu.moviepaging.core.data.vo.Resource
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Keyword
import com.bayu.moviepaging.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface TmdbUseCase {

    fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>>

    fun keywords(query: String): Flow<Resource<List<Keyword>>>

}