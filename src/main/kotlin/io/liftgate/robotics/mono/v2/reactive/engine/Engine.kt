package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.v2.reactive.log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
open class Engine(val composeResource: Engine.(String) -> Resource) : Lifecycle
{
    val parts = mutableMapOf<String, Resource>()
    val terminable = CompositeDisposable()

    override fun use()
    {
        parts.values.forEach(Resource::preUse)
        parts.values.forEach(Resource::use)
        parts.values.forEach(Resource::postUse)
    }

    inline fun <reified T : Resource> composePart(id: String): T
    {
        val part = composeResource.invoke(this, id) as T
        parts[id] = part
        return part
    }

    operator fun set(id: String, resource: Resource)
    {
        parts[id] = resource
    }

    override fun build()
    {
        parts.forEach { (type, part) ->
            part.build()
            log {
                "Binding $type"
            }
        }
    }

    override fun destroy()
    {
        parts.values.forEach(Resource::destroy)
        terminable.dispose()
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
