package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.terminables.composite.CompositeTerminable
import io.liftgate.robotics.mono.v2.reactive.log
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
open class Engine(val composePart: (String) -> Part) : Lifecycle
{
    val parts = mutableMapOf<String, Part>()
    val terminable = CompositeTerminable.create()

    override fun burn()
    {
        parts.values.forEach(Part::ignite)
        parts.values.forEach(Part::burn)
        parts.values.forEach(Part::clean)
    }

    inline fun <reified T : Part> composePart(id: String): T
    {
        val part = composePart.invoke(id) as T
        parts[id] = part
        return part
    }

    operator fun set(id: String, part: Part)
    {
        parts[id] = part
    }

    override fun form()
    {
        parts.forEach { (type, part) ->
            part.form()
            log {
                "Binding $type"
            }
        }
    }

    override fun combust()
    {
        parts.values.forEach(Part::combust)
        terminable.closeAndReportException()
        parts.clear()
    }

    /**
     * Simple DI accessor for other registered components
     * in this [Engine] instance.
     */
    inline fun <reified T : Component> component(id: String) = object : ReadOnlyProperty<Any, T>
    {
        private val instance = parts[id] as T
        override fun getValue(thisRef: Any, property: KProperty<*>) = instance
    }

    /**
     * Simple DI accessor for other registered components
     * in this [Engine] instance.
     *
     * Auto scans for the first registered part with the type.
     */
    inline fun <reified T : Component> component() = object : ReadOnlyProperty<Any, T>
    {
        private val instance = parts.values.firstOrNull { it is T } as T?
            ?: throw IllegalStateException("No part by ${T::class.java.name} found")

        override fun getValue(thisRef: Any, property: KProperty<*>): T = instance
    }
}
