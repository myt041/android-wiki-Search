package com.task.data.repository

import io.reactivex.Single
import com.task.data.mapper.SearchMapper
import com.task.data.source.SearchCache
import com.task.data.source.SearchRemote
import com.task.domain.search.model.SearchResult
import com.task.domain.search.repository.SearchRepository
import javax.inject.Inject

/**
 * Implementation of [SearchRepository].
 */
class SearchDataRepository @Inject constructor(
    private val mapper: SearchMapper,
    private val remote: SearchRemote,
    private val cache: SearchCache
) : SearchRepository {

    override fun search(query: String): Single<List<SearchResult>> {
        return cache.isQueryCached(query)
            .flatMap { isCached ->
                if (isCached) {
                    cache.search(query)
                } else {
                    remote.search(query)
                        .doAfterSuccess { results ->
                            cache.cacheSearch(query, results)
                                .subscribe({
                                    // query cached
                                }, {
                                    // cache failed
                                })
                        }
                }
            }
            .map { entities ->
                entities.map { entity ->
                    mapper.mapFromEntity(entity)
                }
            }
    }

}
