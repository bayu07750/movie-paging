package com.bayu.moviepaging.ui.search.result

import androidx.lifecycle.ViewModel
import com.bayu.moviepaging.domain.usecase.media.TmdbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val useCase: TmdbUseCase,
) : ViewModel() {

    private var searchQuery = MutableStateFlow("")

    fun setSearchQuery(searchQuery: String) {
        this.searchQuery.value = searchQuery
    }

}