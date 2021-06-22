package com.task.wikisearch.inject.modules

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.task.data.source.SearchRemote
import com.task.remote.ApiService
import com.task.remote.ApiServiceFactory
import com.task.remote.SearchRemoteImpl
import com.task.wikisearch.BuildConfig
import java.io.File

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun file(application: Application): File {
            val file = File(application.cacheDir, "OkHttpCache")
            file.mkdirs()
            return file
        }

        @Provides
        @JvmStatic
        fun provideApiService(cacheDir: File): ApiService {
            return ApiServiceFactory.makeApiService(BuildConfig.DEBUG, cacheDir)
        }

    }

    @Binds
    abstract fun bindsSearchRemote(searchRemote: SearchRemoteImpl): SearchRemote

}
