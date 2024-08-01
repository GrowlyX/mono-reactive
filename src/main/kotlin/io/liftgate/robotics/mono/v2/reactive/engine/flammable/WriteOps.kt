package io.liftgate.robotics.mono.v2.reactive.engine.flammable

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
interface WriteOps
{
    fun doWrite()
}

fun prepareWrite(ops: () -> Unit) = object : WriteOps
{
    override fun doWrite() = ops()
}
