package ru.pix.scripts.cmd.trigger.add

import com.flowpowered.math.vector.Vector3i
import com.sk89q.worldedit.BlockVector
import org.spongepowered.api.command.CommandSource
import org.spongepowered.api.command.args.CommandContext
import org.spongepowered.api.entity.living.player.Player
import ru.pix.core.command.executor.userError
import ru.pix.core.commons.math.Area
import ru.pix.easyrent.EasyRent
import ru.pix.scripts.shell.CompiledScript
import ru.pix.scripts.shell.groovy.Trigger

object Common {

    fun getScript(src: CommandSource, ctx: CommandContext): CompiledScript<Trigger> {
        val maybeScript = ctx.getOne<CompiledScript<Trigger>>("script")

        if (!maybeScript.isPresent) {
            throw userError("No trigger script found")
        }

        return maybeScript.get()
    }

    fun getArea(src: Player, ctx: CommandContext): Area {

        val sel = EasyRent.INSTANCE.we.getCuboidSelector(src)
            ?: throw userError("No selection found")

        val p1 = wePosToVec(sel.position1)
        val p2 = wePosToVec(sel.position2)

        return Area(p1, p2)
    }

    private fun wePosToVec(pos: BlockVector): Vector3i {
        return Vector3i(pos.blockX, pos.blockY, pos.blockZ)
    }

}
