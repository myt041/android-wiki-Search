package com.task.wikisearch.main

import com.task.domain.search.model.SearchResult

interface ItemHandler {
    fun invoke(searchResult: SearchResult)
}
