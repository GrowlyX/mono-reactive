package io.liftgate.robotics.mono.v2.reactive.engine.flammable

import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.Part

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class Flammable(engine: Engine) : Part(engine)
{
    private val writeOps = mutableListOf<WriteOps>()
    fun safeWrite(writeOps: WriteOps)
    {
        this.writeOps += writeOps
    }

    override fun burn()
    {

    }

    override fun clean()
    {
        writeOps.onEach(WriteOps::doWrite)
        writeOps.clear()
    }
}
