package com.lalosapps.dialogfragment

import kotlinx.coroutines.delay

object Countdown {

    private const val DEFAULT_DELAY = 5
    private var seconds = DEFAULT_DELAY

    private suspend fun waitOneSecond(): Int {
        delay(1000)
        seconds -= 1
        return seconds
    }

    suspend fun start(delay: Int = DEFAULT_DELAY, timer: (Int) -> Unit) {
        if (delay < 1) return
        seconds = delay
        while (true) {
            try {
                val value = waitOneSecond()
                timer(value)
                if (value == 0) break
            } catch (e: Exception) {
                println("got cancelled")
                break
            }
        }
        println("out of loop")
    }
}