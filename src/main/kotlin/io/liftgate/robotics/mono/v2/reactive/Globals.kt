package io.liftgate.robotics.mono.v2.reactive

import io.liftgate.robotics.mono.v2.reactive.Globals.logSink

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
object Globals
{
    internal var logSink: (String) -> Unit = ::println
    fun bindLogSink(log: (String) -> Unit)
    {
        logSink = log
    }
}

fun log(message: () -> String) = logSink(message())
