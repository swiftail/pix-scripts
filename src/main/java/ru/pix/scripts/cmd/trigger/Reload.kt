package ru.pix.scripts.cmd.trigger

import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor
import ru.pix.scripts.Scripts

object Reload {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .executor(BetterExecutor.simple { src, ctx ->
                Scripts.triggerStorage.reload()
                src.sendMessage(Text.of(TextColors.GREEN, "ok"))
            })
            .build()

    }

}
