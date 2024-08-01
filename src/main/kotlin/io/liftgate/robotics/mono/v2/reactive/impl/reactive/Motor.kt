package io.liftgate.robotics.mono.v2.reactive.impl.reactive

import com.qualcomm.robotcore.hardware.DcMotorEx
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.reactive.Reactive
import io.liftgate.robotics.mono.v2.reactive.engine.reactive.prepareUpdate

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
class ReactiveMotor(engine: Engine, motor: DcMotorEx) : DcMotorEx by motor, Reactive(engine)
{

    override fun preUse()
    {
        mode
        currentPosition
        targetPosition
    }

    fun writeTargetPosition(newTargetPosition: Int)
    {
        safeUpdate(prepareUpdate {
            targetPosition = newTargetPosition
        })
    }

    fun writePower(newPower: Double)
    {
        safeUpdate(prepareUpdate {
            power = newPower
        })
    }

}
