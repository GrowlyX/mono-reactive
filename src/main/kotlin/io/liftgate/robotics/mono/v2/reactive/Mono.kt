package io.liftgate.robotics.mono.v2.reactive

import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.Resource

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
object Mono
{
    fun engine(block: DSLEngine.() -> Unit) = DSLEngine()
        .apply(block)
        .build()
        .apply(Engine::build)
}

class DSLEngine
{
    internal lateinit var composeResourceValue: Engine.(String) -> Resource
    fun composePart(block: Engine.(String) -> Resource)
    {
        this.composeResourceValue = block
    }

    internal fun build(): Engine
    {
        if (!::composeResourceValue.isInitialized)
        {
            throw IllegalStateException(
                "The reactive engine must have a way to compose new Parts. Use composePart { } in the engine builder."
            )
        }

        return Engine(composeResourceValue)
    }
}
