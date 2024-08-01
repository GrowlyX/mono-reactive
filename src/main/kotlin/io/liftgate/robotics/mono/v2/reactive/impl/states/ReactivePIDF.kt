package io.liftgate.robotics.mono.v2.reactive.impl.states

import io.liftgate.robotics.mono.v2.reactive.engine.Resource
import io.liftgate.robotics.mono.v2.reactive.engine.state.ReactiveState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
class ReactivePIDF(resource: Resource) : ReactiveState(resource)
{

    internal val controller = PIDFController(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
    fun configure(block: PIDFController.() -> Unit)
    {
        controller.block()
    }

    override fun update()
    {
        // TODO: get motor & update PIDFController current point and shit
    }

    fun goTo(point: Double): Observable<ReactivePIDFSetPointOps>
    {
        controller.setPoint = point
        return Observable.create {
            val setPointOps = ReactivePIDFSetPointOps(this, it)
            currentOperation = setPointOps
        }
    }

}
