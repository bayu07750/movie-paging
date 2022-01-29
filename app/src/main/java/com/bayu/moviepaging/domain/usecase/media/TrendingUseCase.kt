package com.bayu.moviepaging.domain.usecase.media

import androidx.paging.PagingData
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface TrendingUseCase {

    fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>>

}