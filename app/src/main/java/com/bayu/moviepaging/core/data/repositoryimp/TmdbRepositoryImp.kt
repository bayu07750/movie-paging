package com.bayu.moviepaging.core.data.repositoryimp

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.bayu.moviepaging.core.data.paging.TrendingPagingSource
import com.bayu.moviepaging.core.data.source.remote.RemoteDataSource
import com.bayu.moviepaging.core.data.source.remote.api.ApiResponse
import com.bayu.moviepaging.core.data.vo.Resource
import com.bayu.moviepaging.core.datamappers.KeywordDataMapper.mapResponseToDomain
import com.bayu.moviepaging.core.datamappers.MediaDataMapper.mapResponseToDomain
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Keyword
import com.bayu.moviepaging.domain.model.Media
import com.bayu.moviepaging.domain.repository.TmdbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbRepositoryImp @Inject constructor(
    private val remote: RemoteDataSource
) : TmdbRepository {

    override fun trending(mediaType: MediaType, mediaTime: MediaTime): Flow<PagingData<Media>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            )
        ) {
            TrendingPagingSource(remote, mediaType, mediaTime)
        }.flow.map { pagingData ->
            pagingData.map { response ->
                response.mapResponseToDomain()
            }
        }
    }

    override fun keywords(query: String): Flow<Resource<List<Keyword>>> {
        return flow {
            emit(Resource.Loading())
            when (val response = remote.getKeywordsSearch(query).first()) {
                is ApiResponse.Success -> emit(Resource.Success(response.data!!.mapResponseToDomain()))
                is ApiResponse.Error -> emit(Resource.Error(response.message.toString()))
            }
        }
    }

}