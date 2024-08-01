package io.liftgate.mono.v2.reactive.testing

import io.liftgate.robotics.mono.v2.reactive.engine.Component
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.impl.reactive.ReactiveMotor
import io.liftgate.robotics.mono.v2.reactive.impl.states.ReactivePIDF

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class Lift(engine: Engine) : Component(engine)
{
    private val liftMotor by resource<ReactiveMotor>("frontRight")
    private val liftPID by liftMotor.state<ReactivePIDF> {
        configure {
            p = 0.05
        }
    }

    fun liftTestUpDown()
    {
        liftPID.goTo(1000.0)
            .doAfterNext {
                liftPID.goTo(500.0)
                    .subscribe {
                        println("Completed")
                    }
            }
    }
}
