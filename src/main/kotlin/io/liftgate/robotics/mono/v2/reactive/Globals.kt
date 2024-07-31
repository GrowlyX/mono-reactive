package io.liftgate.robotics.mono.v2.reactive


/**
 * @author GrowlyX
 * @since 7/30/2024
 */
object Globals
{
    private var logSink: (String) -> Unit = ::println
    fun bindLogSink(log: (String) -> Unit)
    {
        logSink = log
    }

    fun log(message: () -> String) = logSink(message())
}
