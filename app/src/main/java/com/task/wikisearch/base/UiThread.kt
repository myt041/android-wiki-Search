package com.task.wikisearch.base

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import com.task.domain.executor.PostExecutionThread
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()

}
