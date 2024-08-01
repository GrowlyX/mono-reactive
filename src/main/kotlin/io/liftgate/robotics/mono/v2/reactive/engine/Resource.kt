package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.v2.reactive.engine.state.ReactiveState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlin.properties.ReadOnlyProperty

/**
 * Represents a system powering a [Component].
 *
 * @author GrowlyX
 * @since 7/30/2024
 */
abstract class Resource(protected val engine: Engine) : Lifecycle
{
    val disposable = CompositeDisposable()
    val states = mutableListOf<ReactiveState>()

    init
    {
        disposable.add(engine.terminable)
    }

    open fun preUse()
    {
        states.forEach(ReactiveState::update)
    }

    open fun postUse()
    {

    }

    override fun use()
    {
        states.forEach {
            it.currentOperation?.update(it)
        }
    }

    override fun build()
    {

    }

    override fun destroy()
    {
        // this is handled w/ the terminable bind
    }

    inline fun <reified T : ReactiveState> state(block: T.() -> Unit = { }): ReadOnlyProperty<Any, T>
    {
        val state = T::class.java
            .getDeclaredConstructor(Resource::class.java)
            .newInstance(this)

        state.block()
        states += state
        return ReadOnlyProperty<Any, T> { _, _ -> state }
    }
}
