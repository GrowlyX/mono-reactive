package io.liftgate.robotics.mono.v2.reactive.engine

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
interface Lifecycle
{
    fun build()
    fun use()
    fun destroy()
}
