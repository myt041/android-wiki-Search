package com.task.data.mapper

import com.task.data.model.SearchEntity
import com.task.domain.search.model.SearchResult
import javax.inject.Inject

open class SearchMapper @Inject constructor() : DataMapper<SearchEntity, SearchResult> {

    override fun mapToEntity(type: SearchResult): SearchEntity {
        return SearchEntity(
            type.pageId,
            type.query,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

    override fun mapFromEntity(type: SearchEntity): SearchResult {
        return SearchResult(
            type.pageId,
            type.query,
            type.index,
            type.title,
            type.description,
            type.imageUrl
        )
    }

}
