package io.liftgate.robotics.mono.v2.reactive.impl.states

import io.liftgate.robotics.mono.v2.reactive.engine.state.ReactiveStateOps
import io.reactivex.rxjava3.core.CompletableEmitter
import io.reactivex.rxjava3.core.ObservableEmitter

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
class ReactivePIDFSetPointOps(
    private val reactivePIDF: ReactivePIDF,
    override val completable: ObservableEmitter<ReactivePIDFSetPointOps>
) : ReactiveStateOps<ReactivePIDFSetPointOps>
{
    override fun hasTargetBeenMet() = reactivePIDF.controller.atSetPoint()
}
