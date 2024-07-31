package io.liftgate.robotics.mono.v2.reactive.engine.coal

import io.liftgate.robotics.mono.terminables.composite.CompositeTerminable
import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.CarbonCycle
import java.util.concurrent.CountDownLatch
import java.util.concurrent.locks.ReentrantLock

/**
 * Represents a component powering the [Engine]. Runs parallel to other [Coal]
 * instances, being signalled to tick ([CarbonCycle]) through a lock condition.
 *
 * @author GrowlyX
 * @since 7/30/2024
 */
abstract class Coal(protected val engine: Engine) : Thread(), CarbonCycle<CountDownLatch>
{
    val terminable = CompositeTerminable.create()

    private val lock = ReentrantLock()
    private val dataReceived = lock.newCondition()

    private var latch: CountDownLatch? = null

    init
    {
        terminable.bind(AutoCloseable {
            interrupt()
        })
        terminable.bindWith(engine.terminable)

        engine.subject.subscribe {
            lock.lock()
            runCatching {
                latch = it
                dataReceived.signal()
            }

            lock.unlock()
        }
    }

    override fun burn(continuation: CountDownLatch)
    {
        continuation.countDown()
    }

    override fun run()
    {
        while (true)
        {
            lock.lock()

            runCatching {
                dataReceived.await()

                runCatching(::burn)
                    .onFailure(Throwable::printStackTrace)

                latch?.countDown()
            }.onFailure(Throwable::printStackTrace)

            lock.unlock()
        }
    }

    abstract fun burn()

    override fun form()
    {
        start()
    }

    override fun combust()
    {
        // this is handled w/ the terminable bind
    }

}
