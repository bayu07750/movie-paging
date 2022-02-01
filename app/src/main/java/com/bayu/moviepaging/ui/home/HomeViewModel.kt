package com.bayu.moviepaging.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayu.moviepaging.core.enums.MediaTime
import com.bayu.moviepaging.core.enums.MediaType
import com.bayu.moviepaging.domain.model.Media
import com.bayu.moviepaging.domain.usecase.media.TmdbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TmdbUseCase
) : ViewModel() {

    private val _mediaType = MutableStateFlow(MediaType.DEFAULT)
    val mediaType: StateFlow<MediaType> = _mediaType

    private val _mediaTime = MutableStateFlow(MediaTime.DEFAULT)
    val mediaTime: StateFlow<MediaTime> = _mediaTime

    val trending: Flow<PagingData<Media>> = combine(_mediaType, _mediaTime) { a, b ->
        Pair(a, b)
    }.flatMapLatest { (type, time) ->
        useCase.trending(type, time)
    }.cachedIn(viewModelScope)

    fun setMediaType(mediaType: MediaType) {
        this._mediaType.value = mediaType
    }

    fun setMediaTime(mediaTime: MediaTime) {
        this._mediaTime.value = mediaTime
    }

}