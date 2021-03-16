package ru.pix.scripts.shell

import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors
import org.spongepowered.api.text.format.TextStyles
import ru.pix.core.commons.getServer
import ru.pix.scripts.Permissions

object ScriptExecutor {

    val logger = org.apache.logging.log4j.LogManager
        .getLogger("ScriptExecutor")

    inline fun execute(script: CompiledScript<*>, context: Array<Any>, executor: () -> Unit) {
        try {
            executor()
        } catch (e: Throwable) {
            logger.error("Error running script ${script.id}", e)
            getServer()
                .onlinePlayers
                .filter { it.hasPermission(Permissions.ADMIN) }
                .forEach {
                    it.sendMessage(Text.of(TextColors.RED, TextStyles.BOLD, "Error running script: ${script.id}"))
                    it.sendMessage(Text.of(TextColors.RED,"Context: ", TextColors.WHITE, context.joinToString()))
                    it.sendMessage(Text.of(e.toString()))
                }
        }
    }

}
