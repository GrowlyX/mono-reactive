package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.terminables.composite.CompositeTerminable
import io.liftgate.robotics.mono.v2.reactive.log

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
}
