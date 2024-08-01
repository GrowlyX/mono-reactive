package io.liftgate.mono.v2.reactive.testing

import io.liftgate.robotics.mono.v2.reactive.engine.Component
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.impl.reactive.ReactiveMotor

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class Drivetrain(engine: Engine) : Component(engine)
{
    private val frontRightMotor by resource<ReactiveMotor>("frontRight")
    private val frontLeftMotor by resource<ReactiveMotor>("frontLeft")
    private val backRightMotor by resource<ReactiveMotor>("backRight")
    private val backLeftMotor by resource<ReactiveMotor>("backLeft")

    fun driveTest()
    {
        frontRightMotor.writePower(1.0)
    }
}
