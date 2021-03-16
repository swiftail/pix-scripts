package ru.pix.scripts.cmd.trigger.add

import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor.Companion.withPlayer
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.Scripts
import ru.pix.scripts.cmd.ScriptArgs
import ru.pix.scripts.trigger.TriggerType

object AreaJoin {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .arguments(
                ScriptArgs.scriptForKind("script", ScriptKind.TRIGGER)
            )
            .executor(withPlayer { src, ctx ->

                val script = Common.getScript(src, ctx)
                val area = Common.getArea(src, ctx)

                val type = TriggerType.AREA_JOIN(area)
                Scripts.triggerStorage.add(script.id, type)
                src.sendMessage(
                    Text.of(
                        TextColors.GREEN,
                        "Added trigger: ",
                        type.describe()
                    )
                )

            })
            .build()

    }

}
