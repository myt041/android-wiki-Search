package com.task.database

import io.reactivex.Completable
import io.reactivex.Single
import com.task.data.model.SearchEntity
import com.task.data.source.SearchCache
import com.task.database.db.SearchDatabase
import com.task.database.mapper.CacheResultMapper
import com.task.database.model.CachedQuery
import java.util.*
import javax.inject.Inject

class SearchCacheImpl @Inject constructor(
    private val searchDatabase: SearchDatabase,
    private val mapper: CacheResultMapper
) : SearchCache {

    override fun search(query: String): Single<List<SearchEntity>> {
        return searchDatabase.queriesDao().getQuery(query)
            .map {
                if (it.isNotEmpty()) {
                    val cachedQuery = it[0]
                    cachedQuery.pageIds
                        .split(",")
                        .map { it.toInt() }
                } else {
                    emptyList()
                }
            }
            .flatMap { pageIds ->
                if (pageIds.isNotEmpty()) {
                    searchDatabase.resultsDao().getResults(pageIds)
                } else {
                    Single.just(emptyList())
                }
            }
            .map { cachedResults ->
                cachedResults.map { cachedResult ->
                    mapper.mapFromCache(query to cachedResult)
                }
            }
    }

    override fun isQueryCached(query: String): Single<Boolean> {
        return searchDatabase.queriesDao().isQueryCached(query)
            .map { it > 0 }
    }

    override fun cacheSearch(query: String, result: List<SearchEntity>): Completable {
        return Completable.defer {
            val cachedQuery = CachedQuery(
                text = query,
                lastCachedTime = Date().time,
                pageIds = result.map { it.pageId }.joinToString(",")
            )

            searchDatabase.queriesDao().insertQuery(cachedQuery)

            val cachedResults = result.map { mapper.mapToCache(it).second }

            searchDatabase.resultsDao().insert(cachedResults)

            Completable.complete()
        }
    }

}
