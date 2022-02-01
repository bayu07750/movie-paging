package com.bayu.moviepaging.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu.moviepaging.core.data.vo.Resource
import com.bayu.moviepaging.domain.model.Keyword
import com.bayu.moviepaging.domain.usecase.media.TmdbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: TmdbUseCase
) : ViewModel() {

    private var _query = MutableStateFlow("")

    private val _keywords: MutableStateFlow<Resource<List<Keyword>>> =
        MutableStateFlow(Resource.Loading())
    val keywords: StateFlow<Resource<List<Keyword>>> = _keywords

    init {
        requestKeyword()
    }

    fun requestKeyword() {
        viewModelScope.launch {
            _query
                .flatMapConcat {
                    if (it.isNotEmpty()) {
                        return@flatMapConcat useCase.keywords(it)
                    } else {
                        flow { }
                    }
                }
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