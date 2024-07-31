package io.liftgate.robotics.mono.v2.reactive.engine

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
interface CarbonCycle<T>
{
    fun form()
    fun burn(continuation: T)
    fun combust()
}
