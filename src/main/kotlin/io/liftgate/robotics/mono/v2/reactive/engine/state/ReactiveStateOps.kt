package io.liftgate.robotics.mono.v2.reactive.engine.state

import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
interface ReactiveStateOps<SELF : ReactiveStateOps<SELF>>
{
    val completable: ObservableEmitter<SELF>
    fun hasTargetBeenMet(): Boolean

    fun update(state: ReactiveState): Boolean
    {
        if (hasTargetBeenMet())
        {
            completable.onComplete()
            return true
        }

        return false
    }
}
