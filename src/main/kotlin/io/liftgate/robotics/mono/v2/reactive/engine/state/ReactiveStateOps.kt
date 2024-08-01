package io.liftgate.robotics.mono.v2.reactive.engine.state

import io.reactivex.rxjava3.core.Completable

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
interface ReactiveStateOps<T>
{
    val completable: Completable
    fun hasTargetBeenMet(current: T): Boolean

    fun update()
    {
        completable.subscribe {  }
    }
}
