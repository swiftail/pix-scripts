package ru.pix.scripts.cmd

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
                val scripts = Scripts.scriptLoader.load()
                src.sendMessage(Text.of(TextColors.GREEN, "Loaded ${scripts} scripts"))
                Scripts.triggerStorage.removeNotExisting()
            })
            .build()

    }

}
