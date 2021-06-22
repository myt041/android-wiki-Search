package com.task.wikisearch.inject.modules

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.task.domain.executor.PostExecutionThread
import com.task.wikisearch.base.UiThread
import com.task.wikisearch.main.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

}
