package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.v2.reactive.engine.state.ReactiveState
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Represents a system powering a [Component].
 *
 * @author GrowlyX
 * @since 7/30/2024
 */
abstract class Resource(protected val engine: Engine) : Lifecycle
{
    val disposable = CompositeDisposable()
    val states = mutableMapOf<String, ReactiveState<*>>()

    init
    {
        disposable.add(engine.terminable)
    }

    open fun preUse()
    {

    }

    open fun postUse()
    {

    }

    override fun use()
    {
        states.forEach { (t, u) ->
            u.currentOperation?.update()
        }
    }

    override fun build()
    {

    }

    override fun destroy()
    {
        // this is handled w/ the terminable bind
    }

}
