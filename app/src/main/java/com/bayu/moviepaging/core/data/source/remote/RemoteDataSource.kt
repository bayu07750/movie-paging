package com.bayu.moviepaging.core.data.source.remote

import com.bayu.moviepaging.core.data.source.remote.api.ApiResponse
import com.bayu.moviepaging.core.data.source.remote.api.ApiService
import com.bayu.moviepaging.core.data.source.remote.api.response.KeywordResponse
import com.bayu.moviepaging.core.data.source.remote.api.response.MediaResponse
import com.bayu.moviepaging.core.di.IODispatcher
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    fun getTrending(
        mediaType: MediaType,
        mediaTime: MediaTime,
        page: Int,
    ): Flow<ApiResponse<List<MediaResponse>>> {
        return flow {
            try {
                val response = apiService.getTrending(
                    type = mediaType.type.lowercase(),
                    time = mediaTime.time,
                    page = page
                )
                if (response.results?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Success(emptyList()))
                }
            } catch (e: IOException) {
                emit(ApiResponse.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(ioDispatcher)
    }

    fun getKeywordsSearch(query: String): Flow<ApiResponse<List<KeywordResponse>>> {
        return flow {
            try {
                val response = apiService.getKeywordsSearch(query = query)
                if (response.results?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Success(emptyList()))
                }
            } catch (e: IOException) {
                emit(ApiResponse.Error(e.message.toString()))
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

}