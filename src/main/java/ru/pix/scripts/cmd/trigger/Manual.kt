package ru.pix.scripts.cmd.trigger

import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.text.Text
import ru.pix.core.command.executor.BetterExecutor.Companion.simple
import ru.pix.scripts.cmd.ScriptArgs
import ru.pix.scripts.cmd.trigger.add.Common
import ru.pix.scripts.shell.ScriptExecutor
import ru.pix.scripts.trigger.TriggerType

object Manual {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .arguments(
                ScriptArgs.triggerScript("script", TriggerType.MANUAL::class.java),
                GenericArguments.remainingJoinedStrings(Text.of("args"))
            )
            .executor(simple { src, ctx ->

                val script = Common.getScript(src, ctx)
                val args = ctx.getOne<String>("args")
                    .map { s -> s.trim().split(" ").filterNot { it.isEmpty() } }
                    .orElse(listOf())

                ScriptExecutor.execute(script, arrayOf("manual", src.toString())) {
                    script.groovyScript.emit(
                        src as? Player,
                        mapOf(
                            "source" to "command",
                            "args" to args
                        )
                    )
                    src.sendMessage(Text.of("Emit ok"))
                }

            })
            .build()

    }

}
