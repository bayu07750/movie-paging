package com.bayu.moviepaging.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bayu.moviepaging.core.constant.Const
import com.bayu.moviepaging.core.data.source.remote.RemoteDataSource
import com.bayu.moviepaging.core.data.source.remote.api.ApiResponse
import com.bayu.moviepaging.core.data.source.remote.api.response.MediaResponse
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import kotlinx.coroutines.flow.first

class TrendingPagingSource(
    private val remote: RemoteDataSource,
    private val mediaType: MediaType,
    private val mediaTime: MediaTime,
) : PagingSource<Int, MediaResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MediaResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MediaResponse> {
        val position = params.key ?: Const.Paging.STARTING_INDEX_PAGE_TMDB

        return when (val response = remote.getTrending(mediaType, mediaTime, position).first()) {
            is ApiResponse.Error -> LoadResult.Error(Throwable(response.message))
            is ApiResponse.Success -> LoadResult.Page(
                data = response.data ?: emptyList(),
                prevKey = if (position == Const.Paging.STARTING_INDEX_PAGE_TMDB) null else position - 1,
                nextKey = if (response.data?.isEmpty() == true) null else position + 1
            )
        }
    }
}