package io.liftgate.robotics.mono.v2.reactive.engine

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A group of [Resource] instances that work in-sync
 * to create a single end result.
 *
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class Component(engine: Engine) : Resource(engine)
{
    val parts = mutableMapOf<String, Resource>()
    override fun destroy()
    {
        parts.clear()
    }

    override fun preUse()
    {
        parts.values.forEach(Resource::preUse)
    }

    override fun use()
    {
        parts.values.forEach(Resource::use)
    }

    override fun postUse()
    {
        parts.values.forEach(Resource::postUse)
    }

    protected inline fun <reified T : Resource> resource(
        id: String,
        crossinline configure: T.() -> Unit = { }
    ) = object : ReadOnlyProperty<Any, T>
    {
        private val instance = engine.composePart<T>(id)
        init
        {
            parts[id] = instance
            instance.configure()
            instance.disposable.add(this@Component.disposable)
        }

        override fun getValue(thisRef: Any, property: KProperty<*>) = instance
    }
}
