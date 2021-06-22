package com.task.wikisearch.main

import android.content.Context
import android.support.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import com.task.domain.search.model.SearchResult
import com.task.wikisearch.R
import javax.inject.Inject

class OpenWikipediaPageHandler @Inject constructor(
    private val context: Context
) : ItemHandler {

    override operator fun invoke(searchResult: SearchResult) {
        val primary = context.getColor(R.color.primary)
        val uri = "https://en.m.wikipedia.org/?curid=${searchResult.pageId}".toUri()

        CustomTabsIntent.Builder()
            .setToolbarColor(primary)
            .addDefaultShareMenuItem()
            .enableUrlBarHiding()
            .build()
            .launchUrl(context, uri)
    }

}
