package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.terminables.composite.CompositeTerminable
import io.liftgate.robotics.mono.v2.reactive.tooling.BurnOps

/**
 * Represents a system powering the [Engine].
 *
 * @author GrowlyX
 * @since 7/30/2024
 */
abstract class Part(protected val engine: Engine) : Lifecycle
{
    val terminable = CompositeTerminable.create()
    val burnOps = mutableListOf<BurnOps>()

    init
    {
        terminable.bindWith(engine.terminable)
    }

    abstract fun ignite()
    abstract fun clean()

    override fun form()
    {

    }

    override fun combust()
    {
        // this is handled w/ the terminable bind
    }

}
