package io.liftgate.robotics.mono.v2.reactive.engine.state

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class ReactiveState<T>
{
    var currentOperation: ReactiveStateOps<T>? = null
    abstract fun currentState(): T
}
