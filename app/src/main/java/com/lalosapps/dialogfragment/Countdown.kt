package com.lalosapps.dialogfragment

import kotlinx.coroutines.delay

object Countdown {

    private const val DEFAULT_DELAY = 5
    private var seconds = DEFAULT_DELAY
    private var stopTimer = false

    private suspend fun waitOneSecond(): Int {
        delay(1000)
        seconds -= 1
        return seconds
    }

    suspend fun start(delay: Int = DEFAULT_DELAY, timer: (Int) -> Unit) {
        stopTimer = false
        seconds = delay
        while (!stopTimer) {
            val value = waitOneSecond()
            timer(value)
            if (value == 0) break
        }
    }

    fun stop() {
        stopTimer = true
    }
}