package com.task.presentation

import com.task.domain.search.model.SearchResult

sealed class SearchState {

    class Error(val message: String) : SearchState()

    class Success(val result: List<SearchResult>) : SearchState()

}
