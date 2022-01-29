package com.bayu.moviepaging.core.data.source.remote

import com.bayu.moviepaging.core.data.source.remote.api.ApiResponse
import com.bayu.moviepaging.core.data.source.remote.api.ApiService
import com.bayu.moviepaging.core.data.source.remote.api.response.MediaResponse
import com.bayu.moviepaging.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    fun getGreeting(
        type: String,
        time: String,
        page: Int
    ): Flow<ApiResponse<List<MediaResponse>>> {
        return flow {
            try {
                val response = apiService.getTrending(type = type, time = time, page = page)
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