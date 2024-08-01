package io.liftgate.robotics.mono.v2.reactive.engine.state

import io.liftgate.robotics.mono.v2.reactive.engine.Resource

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class ReactiveState(private val resource: Resource)
{
    var currentOperation: ReactiveStateOps<*>? = null
    abstract fun update()
}
