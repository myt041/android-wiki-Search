package com.task.data.model

/**
 * SearchEntity for the data layer.
 *
 * Data layer will only accept Entity models from database and remote layers.
 */
data class SearchEntity(
    val pageId: Int,
    val query: String,
    val index: Int,
    val title: String,
    val description: String?,
    val imageUrl: String?
)
