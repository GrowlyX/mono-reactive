package io.liftgate.robotics.mono.v2.reactive.engine.reactive

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
interface ResourceUpdateOps
{
    fun update()
}

fun prepareUpdate(ops: () -> Unit) = object : ResourceUpdateOps
{
    override fun update() = ops()
}
