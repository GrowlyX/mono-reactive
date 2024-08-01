package io.liftgate.robotics.mono.v2.reactive

import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.Part

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
object Mono
{
    fun engine(block: DSLEngine.() -> Unit) = DSLEngine()
        .apply(block)
        .build()
        .apply(Engine::form)
}

class DSLEngine
{
    internal lateinit var composePartValue: Engine.(String) -> Part
    fun composePart(block: Engine.(String) -> Part)
    {
        this.composePartValue = block
    }

    internal fun build(): Engine
    {
        if (!::composePartValue.isInitialized)
        {
            throw IllegalStateException(
                "The reactive engine must have a way to compose new Parts. Use composePart { } in the engine builder."
            )
        }

        return Engine(composePartValue)
    }
}
