package io.liftgate.robotics.mono.v2.reactive.engine

import io.liftgate.robotics.mono.terminables.composite.CompositeTerminable
import io.liftgate.robotics.mono.v2.reactive.Globals
import io.liftgate.robotics.mono.v2.reactive.engine.coal.Coal
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * @author GrowlyX
 * @since 7/30/2024
 */
class Engine : CarbonCycle<Any>
{
    private val coal = mutableMapOf<KClass<*>, Coal>()

    val terminable = CompositeTerminable.create()
    val subject = PublishSubject.create<CountDownLatch>()

    init
    {
        terminable.with {
            subject.onComplete()
        }
    }

    fun tick()
    {
        burn(Unit)
    }

    override fun burn(continuation: Any)
    {
        val latch = CountDownLatch(coal.values.size)
        subject.onNext(latch)

        latch.await(100L, TimeUnit.MILLISECONDS)
    }

    operator fun plusAssign(coal: Coal)
    {
        this.coal[coal::class] = coal
    }

    override fun form()
    {
        coal.forEach { (type, coal) ->
            coal.form()
            Globals.log {
                "Binding ${type.qualifiedName}"
            }
        }
    }

    override fun combust()
    {
        terminable.closeAndReportException()
    }
}
