package io.liftgate.robotics.mono.v2.reactive.engine.flammable

import com.qualcomm.robotcore.hardware.DcMotorEx
import io.liftgate.robotics.mono.v2.reactive.engine.Engine

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
class FlammableMotor(engine: Engine, motor: DcMotorEx) : DcMotorEx by motor, Flammable(engine)
{

    override fun ignite()
    {
        mode
        currentPosition
        targetPosition
    }

    fun writeTargetPosition(newTargetPosition: Int)
    {
        safeWrite(prepareWrite {
            targetPosition = newTargetPosition
        })
    }

}
