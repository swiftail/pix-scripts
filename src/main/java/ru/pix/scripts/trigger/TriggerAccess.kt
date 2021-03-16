package ru.pix.scripts.trigger

import com.flowpowered.math.vector.Vector3i
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import ru.pix.core.commons.math.Area
import ru.pix.scripts.shell.CompiledScript
import ru.pix.scripts.shell.groovy.Trigger

class TriggerAccess {

    private val cachedClickTriggers: Multimap<Vector3i, CompiledScript<Trigger>> = ArrayListMultimap.create()

    private val cachedWalkTriggers: Multimap<Vector3i, CompiledScript<Trigger>> = ArrayListMultimap.create()

    private val cachedAreaJoinTriggers: Multimap<Area, CompiledScript<Trigger>> = ArrayListMultimap.create()

    private val cachedAreaLeaveTriggers: Multimap<Area, CompiledScript<Trigger>> = ArrayListMultimap.create()

    private val cachedAreas = mutableSetOf<Area>()

    // Recalculate should be called when TriggerStorage is updated
    fun recalculate(storage: TriggerStorage) {

        cachedClickTriggers.clear()
        storage
            .scriptsForType(TriggerType.CLICK::class.java)
            .forEach { (script, type) ->
                cachedClickTriggers.put(type.block, script)
            }

        cachedWalkTriggers.clear()
        storage
            .scriptsForType(TriggerType.WALK::class.java)
            .forEach { (script, type) ->
                cachedWalkTriggers.put(type.block, script)
            }

        cachedAreas.clear()

        cachedAreaJoinTriggers.clear()
        storage
            .scriptsForType(TriggerType.AREA_JOIN::class.java)
            .forEach { (script, type) ->
                cachedAreas.add(type.area)
                cachedAreaJoinTriggers.put(type.area, script)
            }

        cachedAreaLeaveTriggers.clear()
        storage
            .scriptsForType(TriggerType.AREA_LEAVE::class.java)
            .forEach { (script, type) ->
                cachedAreas.add(type.area)
                cachedAreaLeaveTriggers.put(type.area, script)
            }
    }

    fun clickTriggers(pos: Vector3i): Collection<CompiledScript<Trigger>> {
        return cachedClickTriggers[pos]
    }

    fun walkTriggers(pos: Vector3i): Collection<CompiledScript<Trigger>> {
        return cachedWalkTriggers[pos]
    }

    fun areaJoinTriggers(area: Area): Collection<CompiledScript<Trigger>> {
        return cachedAreaJoinTriggers[area]
    }

    fun areaLeaveTriggers(area: Area): Collection<CompiledScript<Trigger>> {
        return cachedAreaLeaveTriggers[area]
    }

    fun areas(pos: Vector3i): Collection<Area> {
        return cachedAreas.filter { pos.toDouble() in it }
    }

}
