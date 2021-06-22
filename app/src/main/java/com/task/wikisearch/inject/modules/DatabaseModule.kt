package com.task.wikisearch.inject.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.task.data.source.SearchCache
import com.task.database.SearchCacheImpl
import com.task.database.db.SearchDatabase

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): SearchDatabase {
            return SearchDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindSearchCache(searchDatabase: SearchCacheImpl): SearchCache

}
