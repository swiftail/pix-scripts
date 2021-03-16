package ru.pix.scripts.cmd

import org.spongepowered.api.command.spec.CommandSpec
import ru.pix.scripts.cmd.trigger.Add
import ru.pix.scripts.cmd.trigger.List
import ru.pix.scripts.cmd.trigger.Reload

object Trigger {

    fun spec(): CommandSpec {

        return CommandSpec
            .builder()
            .children(mapOf(
                listOf("list") to List.spec(),
                listOf("add") to Add.spec(),
                listOf("reload") to Reload.spec()
            ))
            .build()

    }

}
