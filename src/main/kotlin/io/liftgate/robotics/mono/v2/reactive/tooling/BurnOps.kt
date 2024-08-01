package io.liftgate.robotics.mono.v2.reactive.tooling

import io.liftgate.robotics.mono.terminables.Terminable
import io.reactivex.rxjava3.core.Completable

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
interface BurnOps : Terminable
{
    val completable: Completable
    fun isActive(): Boolean
}
