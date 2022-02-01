package com.bayu.moviepaging.domain.usecase.media

import androidx.paging.PagingData
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Media
import com.bayu.moviepaging.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbInteraction @Inject constructor(
    private val tmdbRepository: TmdbRepository
) : TmdbUseCase {

    override fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>> {
        return tmdbRepository.trending(mediaType, mediaTime)
    }

}