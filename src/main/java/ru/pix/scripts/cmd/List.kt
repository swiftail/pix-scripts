package ru.pix.scripts.cmd

import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.action.TextActions
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor
import ru.pix.locations.Haste
import ru.pix.scripts.Scripts

object List {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .executor(BetterExecutor.simple { src, ctx ->
                val tb = Text.builder()
                val map = Scripts.registry.getAll()

                tb.append(Text.of(TextColors.GREEN, "Scripts:\n"))

                for ((kind, scripts) in map) {
                    tb.append(Text.of(TextColors.YELLOW, kind, "(", scripts.size, "):\n"))
                    scripts.forEach { script ->
                        tb.append(
                            Text.of(script.textId, "\n")
                                .toBuilder()
                                .onHover(TextActions.showText(Text.of("Hover to view")))
                                .onClick(TextActions.executeCallback {
                                    val source = script.source
                                    Haste.postWithText(source, src::sendMessage)
                                })
                                .build()
                        )
                    }
                }

                src.sendMessage(tb.build())

            })
            .build()

    }

}
