package com.task.domain.search

import io.reactivex.Single
import com.task.domain.executor.PostExecutionThread
import com.task.domain.search.model.SearchResult
import com.task.domain.search.repository.SearchRepository
import com.task.domain.usecases.SingleUseCase
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    postExecutionThread: PostExecutionThread,
    private val searchRepository: SearchRepository
) : SingleUseCase<List<SearchResult>, SearchUseCase.Companion.Params>(postExecutionThread) {

    override fun buildUseCase(params: Params): Single<List<SearchResult>> {
        return searchRepository.search(params.query)
    }

    companion object {
        data class Params(val query: String)
    }

}
