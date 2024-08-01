package io.liftgate.robotics.mono.v2.reactive.engine

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A group of [Part] instances that work in-sync
 * to create a single end result.
 *
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class Component(engine: Engine) : Part(engine)
{
    val parts = mutableMapOf<String, Part>()
    override fun combust()
    {
        parts.clear()
    }

    override fun ignite()
    {
        parts.values.forEach(Part::ignite)
    }

    override fun burn()
    {
        parts.values.forEach(Part::burn)
    }

    override fun clean()
    {
        parts.values.forEach(Part::clean)
    }

    protected inline fun <reified T : Part> part(id: String) = object : ReadOnlyProperty<Any, T>
    {
        private val instance = engine.composePart<T>(id)
        init
        {
            parts[id] = instance
            instance.terminable.bindWith(this@Component.terminable)
        }

        override fun getValue(thisRef: Any, property: KProperty<*>) = instance
    }
}
