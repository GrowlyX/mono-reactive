package io.liftgate.mono.v2.reactive.testing

import io.liftgate.robotics.mono.v2.reactive.engine.Component
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.impl.flammables.FlammableMotor

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class Drivetrain(engine: Engine) : Component(engine)
{
    private val frontRightMotor by part<FlammableMotor>("frontRight")
    private val frontLeftMotor by part<FlammableMotor>("frontLeft")
    private val backRightMotor by part<FlammableMotor>("backRight")
    private val backLeftMotor by part<FlammableMotor>("backLeft")

    fun driveTest()
    {
        frontRightMotor.writePower(1.0)
    }
}
