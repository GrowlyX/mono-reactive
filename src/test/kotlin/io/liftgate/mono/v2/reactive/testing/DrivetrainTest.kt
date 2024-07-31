package io.liftgate.mono.v2.reactive.testing

import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.coal.Coal

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class DrivetrainTest(engine: Engine, private val instance: Int) : Coal(engine)
{
    override fun burn()
    {
        // Let's imagine this is something slow
        println("Coal #$instance burns silently...")
        sleep(50L)
    }
}


class DrivetrainTest2(engine: Engine, private val instance: Int) : Coal(engine)
{
    override fun burn()
    {
        // Let's imagine this is something slow
        println("Coal #$instance burns silently...")
        sleep(50L)
    }
}
