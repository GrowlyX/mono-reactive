package io.liftgate.robotics.mono.v2.reactive.engine

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class CompositePart(engine: Engine) : Part(engine)
{
    val parts = mutableMapOf<String, Part>()
    override fun combust()
    {
        parts.clear()
    }

    protected inline fun <reified T : Part> part(id: String) = object : ReadOnlyProperty<Any, T>
    {
        private val instance = engine.composePart<T>(id)
        init
        {
            parts[id] = instance
            instance.terminable.bindWith(this@CompositePart.terminable)
        }

        override fun getValue(thisRef: Any, property: KProperty<*>) = instance
    }
}
