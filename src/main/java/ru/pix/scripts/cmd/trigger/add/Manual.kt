package ru.pix.scripts.cmd.trigger.add

import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor.Companion.simple
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.Scripts
import ru.pix.scripts.cmd.ScriptArgs
import ru.pix.scripts.trigger.TriggerType

object Manual {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .arguments(
                ScriptArgs.scriptForKind("script", ScriptKind.TRIGGER)
            )
            .executor(simple { src, ctx ->
                val script = Common.getScript(src, ctx)

                Scripts.triggerStorage.add(script.id, TriggerType.MANUAL)
                src.sendMessage(Text.of(TextColors.GREEN, "Ok"))
            })
            .build()

    }

}
