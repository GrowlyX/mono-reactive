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
            this += DrivetrainTest(this, 1)
            this += DrivetrainTest2(this, 2)
        }

        repeat(5) { repetition ->
            measureTimeMillis { engine.tick() }.apply {
                println("Took $this for $repetition")
            }
        }
    }

}
