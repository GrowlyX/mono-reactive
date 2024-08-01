package io.liftgate.robotics.mono.v2.reactive.engine.reactive

import io.liftgate.robotics.mono.v2.reactive.engine.Engine
import io.liftgate.robotics.mono.v2.reactive.engine.Resource

/**
 * @author GrowlyX
 * @since 8/1/2024
 */
abstract class Reactive(engine: Engine) : Resource(engine)
{
    private val resourceUpdateOps = mutableListOf<ResourceUpdateOps>()
    fun safeUpdate(resourceUpdateOps: ResourceUpdateOps)
    {
        this.resourceUpdateOps += resourceUpdateOps
    }

    override fun use()
    {

    }

    override fun postUse()
    {
        resourceUpdateOps.onEach(ResourceUpdateOps::update)
        resourceUpdateOps.clear()
    }
}
