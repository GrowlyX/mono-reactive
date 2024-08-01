package io.liftgate.robotics.mono.v2.reactive.impl.flammables

import com.qualcomm.robotcore.hardware.DcMotorEx
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.flammable.Flammable
import io.liftgate.robotics.mono.v2.reactive.engine.flammable.prepareWrite

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

    fun writePower(newPower: Double)
    {
        safeWrite(prepareWrite {
            power = newPower
        })
    }

}
