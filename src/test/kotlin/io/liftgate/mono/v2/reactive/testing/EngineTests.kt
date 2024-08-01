package io.liftgate.mono.v2.reactive.testing

import io.liftgate.robotics.mono.v2.reactive.Mono
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class EngineTests
{

    @Test
    fun `test parallel run`()
    {
        val engine = Mono.engine {
            this["drivetrain"] = Drivetrain(this)
        }
    }

}
