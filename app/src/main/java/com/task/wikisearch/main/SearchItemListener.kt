package com.task.wikisearch.main

import com.task.domain.search.model.SearchResult

interface SearchItemListener {
    fun onSearchResultClicked(searchResult: SearchResult)
}
