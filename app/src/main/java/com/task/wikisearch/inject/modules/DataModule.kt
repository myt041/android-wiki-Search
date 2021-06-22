package com.task.wikisearch.inject.modules

import dagger.Binds
import dagger.Module
import com.task.data.repository.SearchDataRepository
import com.task.domain.search.repository.SearchRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsSearchRepository(searchRepository: SearchDataRepository): SearchRepository

}
