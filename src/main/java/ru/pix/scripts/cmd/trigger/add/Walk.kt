package ru.pix.scripts.cmd.trigger.add

import com.flowpowered.math.vector.Vector3d
import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor.Companion.simple
import ru.pix.core.commons.getServer
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.Scripts
import ru.pix.scripts.cmd.ScriptArgs
import ru.pix.scripts.trigger.TriggerType

object Walk {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .arguments(
                ScriptArgs.scriptForKind("script", ScriptKind.TRIGGER),
                GenericArguments.vector3d(Text.of("block"))
            )
            .executor(simple { src, ctx ->

                val script = Common.getScript(src, ctx)

                val vec = ctx.getOne<Vector3d>("block").get().toInt()
                val block = getServer().worlds.first().getBlock(vec)

                Scripts.triggerStorage.add(script.id, TriggerType.WALK( vec ))
                src.sendMessage(Text.of(TextColors.GREEN, "Added WalkTrigger to ", TextColors.YELLOW, block.type, "@", vec))

            })
            .build()

    }

}
