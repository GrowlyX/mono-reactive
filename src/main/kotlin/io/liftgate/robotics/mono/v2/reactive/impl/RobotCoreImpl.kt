package io.liftgate.robotics.mono.v2.reactive.impl

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotorEx
import io.liftgate.robotics.mono.v2.reactive.Mono
import io.liftgate.robotics.mono.v2.reactive.impl.flammables.FlammableMotor

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
fun LinearOpMode.lynxEngine() = Mono.engine {
    composePart {
        val hardware = hardwareMap.get(it)
        when (true)
        {
            (hardware is DcMotorEx) -> FlammableMotor(this, hardware)
            else -> throw IllegalArgumentException(
                "Unsupported device $it of type ${hardware.javaClass.name}"
            )
        }
    }
}
