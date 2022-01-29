package com.bayu.moviepaging.domain.usecase.media

import androidx.paging.PagingData
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Media
import com.bayu.moviepaging.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingInteraction @Inject constructor(
    private val trendingRepository: TrendingRepository
) : TrendingUseCase {

    override fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>> {
        return trendingRepository.trending(mediaType, mediaTime)
    }

}