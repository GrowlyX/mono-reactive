package io.liftgate.robotics.mono.v2.reactive

import io.liftgate.robotics.mono.v2.reactive.engine.Engine

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
object Mono
{
    fun engine(block: Engine.() -> Unit) = Engine().apply(block).apply(Engine::form)
}
