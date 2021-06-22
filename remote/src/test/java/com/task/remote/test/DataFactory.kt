package com.task.remote.test

import java.util.*
import kotlin.math.roundToInt

object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Random().nextInt()
    }

}
