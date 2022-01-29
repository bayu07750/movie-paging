package com.bayu.moviepaging.core.data.repositoryimp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayu.moviepaging.core.data.paging.TrendingPagingSource
import com.bayu.moviepaging.core.data.source.remote.RemoteDataSource
import com.bayu.moviepaging.core.datamappers.MediaDataMapper.mapResponseToDomain
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Media
import com.bayu.moviepaging.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrendingRepositoryImp @Inject constructor(
    private val remote: RemoteDataSource
) : TrendingRepository {

    override fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
            )
        ) {
            TrendingPagingSource(remote, mediaType, mediaTime)
        }.flow.map { pagingData ->
            pagingData.map { response ->
                response.mapResponseToDomain()
            }
        }
    }

}