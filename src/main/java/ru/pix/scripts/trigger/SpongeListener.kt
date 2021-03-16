package ru.pix.scripts.trigger

import com.flowpowered.math.vector.Vector3i
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import org.spongepowered.api.block.BlockSnapshot
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.block.InteractBlockEvent
import org.spongepowered.api.event.entity.MoveEntityEvent
import org.spongepowered.api.event.filter.cause.First
import ru.pix.core.commons.math.Area
import ru.pix.scripts.Scripts
import ru.pix.scripts.shell.ScriptExecutor
import java.util.*

class SpongeListener {

    private val playerAreasMap: Multimap<UUID, Area> = ArrayListMultimap.create()

    @Listener
    fun onPlayerClickBlock(
        event: InteractBlockEvent.Secondary.MainHand,
        @First player: Player
    ) {
        val tb = event.targetBlock

        if (tb == BlockSnapshot.NONE) {
            return
        }

        val blockType = tb.state.type

        Scripts
            .triggerStorage
            .scriptsForType(TriggerType.CLICK::class.java)
            .filter { (_, type) ->
                type.block == tb.position
            }
            .forEach { (script, _) ->

                ScriptExecutor.execute(script, arrayOf("click", player.name, tb.position)) {
                    script.groovyScript.emit(
                        player, mapOf(
                            "source" to "click",
                            "targetBlock" to tb,
                            "blockType" to blockType
                        )
                    )
                }

            }

    }

    fun <T> diff(s1: Collection<T>, s2: Collection<T>): Pair<Set<T>, Set<T>> {
        return (s1.subtract(s2)) to (s2.subtract(s1))
    }

    fun checkPlayerAreas(access: TriggerAccess, player: Player, pos: Vector3i) {

        val playerAreas = access.areas(pos)
        val prev = playerAreasMap[player.uniqueId]

        val (joined, left) = diff(playerAreas, prev)

        for (area in joined) {
            access.areaJoinTriggers(area).forEach { trigger ->
                ScriptExecutor.execute(trigger, arrayOf("areaJoin", player.name)) {
                    trigger.groovyScript.emit(player, mapOf(
                        "source" to "areaJoin"
                    ))
                }
            }
        }

        for (area in left) {
            access.areaLeaveTriggers(area).forEach { trigger ->
                ScriptExecutor.execute(trigger, arrayOf("areaLeave", player.name)) {
                    trigger.groovyScript.emit(player, mapOf(
                        "source" to "areaLeave"
                    ))
                }
            }
        }

        playerAreasMap.replaceValues(player.uniqueId, playerAreas)
    }

    @Listener
    fun onPlayerMove(event: MoveEntityEvent, @First player: Player) {

        val from = event.fromTransform
        val to = event.toTransform

        if (
            from.location.blockX != to.location.blockX
            || from.location.blockY != to.location.blockY
            || from.location.blockZ != to.location.blockZ
        ) {
            val access = Scripts.triggerStorage.access()

            val pos = to.location.blockPosition

            for (walkTrigger in access.walkTriggers(pos)) {
                // TODO WRAP

                ScriptExecutor.execute(walkTrigger, arrayOf("walk", player.name, pos)) {
                    walkTrigger.groovyScript.emit(
                        player, mapOf(
                            "source" to "walk",
                            "targetBlock" to pos
                        )
                    )
                }

            }

            checkPlayerAreas(access, player, pos)
        }
    }

}
