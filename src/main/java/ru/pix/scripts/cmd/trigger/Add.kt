package ru.pix.scripts.cmd.trigger

import org.spongepowered.api.command.spec.CommandSpec
import ru.pix.scripts.cmd.trigger.add.AreaJoin
import ru.pix.scripts.cmd.trigger.add.AreaLeave
import ru.pix.scripts.cmd.trigger.add.Click
import ru.pix.scripts.cmd.trigger.add.Walk

object Add {

    fun spec() : CommandSpec {

        return CommandSpec
            .builder()
            .children(mapOf(
                listOf("manual") to Manual.spec(),
                listOf("click") to Click.spec(),
                listOf("walk") to Walk.spec(),
                listOf("area-join") to AreaJoin.spec(),
                listOf("area-leave") to AreaLeave.spec()
            ))
            .build()

    }

}
