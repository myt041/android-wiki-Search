package com.task.remote

import io.reactivex.Single
import com.task.data.model.SearchEntity
import com.task.data.source.SearchRemote
import com.task.remote.mapper.SearchEntityMapper
import javax.inject.Inject

/**
 * Implementation of [SearchRemote].
 */
class SearchRemoteImpl @Inject constructor(
    private val mapper: SearchEntityMapper,
    private val service: ApiService
) : SearchRemote {

    override fun search(query: String): Single<List<SearchEntity>> {
        return service.search(query)
            .map { response ->
                response.query.pages
                    .sortedBy { it.index }
                    .map { page -> mapper.mapToEntity(query to page) }
            }
    }

}
