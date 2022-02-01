package com.bayu.moviepaging.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu.moviepaging.core.data.vo.Resource
import com.bayu.moviepaging.domain.model.Keyword
import com.bayu.moviepaging.domain.usecase.media.TmdbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel(
    private val useCase: TmdbUseCase
) : ViewModel() {

    private var _query = MutableStateFlow("")

    private val _keywords: MutableStateFlow<Resource<List<Keyword>>> =
        MutableStateFlow(Resource.Loading())
    private val keyword: StateFlow<Resource<List<Keyword>>> = _keywords

    init {
        requestKeyword()
    }

    fun requestKeyword() {
        viewModelScope.launch {
            _query
                .flatMapConcat { useCase.keywords(it) }
                .collect {
                    _keywords.value = it
                }
        }
    }

    fun setQuery(query: String) {
        if (query.isNotEmpty()) {
            _query.value = query
        }
    }

}