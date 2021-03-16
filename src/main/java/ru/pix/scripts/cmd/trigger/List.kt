package ru.pix.scripts.cmd.trigger

import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.action.TextActions
import org.spongepowered.api.text.format.TextColors
import ru.pix.core.command.executor.BetterExecutor
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.Scripts

object List {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .executor(BetterExecutor.simple { src, ctx ->

                val obj = Scripts.triggerStorage.get()
                val triggers = obj.triggers

                val tb = Text.builder()

                tb.append(Text.of(TextColors.GREEN, "Triggers:\n"))

                triggers.forEach { entry ->
                    val (type, id) = entry
                    val script = Scripts.registry.getScript(ScriptKind.TRIGGER, id)!!
                    tb.append(
                        script.textId
                            .toBuilder()
                            .onHover(
                                TextActions
                                    .showText(
                                        Text.join(
                                            Text.of(TextColors.RED, "[CLICK TO REMOVE]\n"),
                                            type.describe()
                                        )
                                    )
                            )
                            .onClick(
                                TextActions
                                    .executeCallback {
                                        if(Scripts.triggerStorage.remove(entry)) {
                                            src.sendMessage(Text.of(TextColors.GREEN, "Ok"))
                                        }
                                    }
                            )
                            .build()
                    ).append(Text.of("\n"))
                }

                src.sendMessage(tb.build())

            })
            .build()

    }

}
