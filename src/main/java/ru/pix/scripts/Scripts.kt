package ru.pix.scripts

import org.slf4j.Logger
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.spec.CommandSpec
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import ru.pix.core.commons.PixFiles
import ru.pix.core.misc.lifecycle.WithLifecycle
import ru.pix.scripts.cmd.List
import ru.pix.scripts.cmd.Reload
import ru.pix.scripts.cmd.Trigger
import ru.pix.scripts.shell.ScriptLoader
import ru.pix.scripts.shell.ScriptRegistry
import ru.pix.scripts.shell.globals.GlobalsBuilder
import ru.pix.scripts.trigger.SpongeListener
import ru.pix.scripts.trigger.TriggerStorage
import java.nio.file.Path
import javax.inject.Inject

@Plugin(id = "scripts", name = "Pix Scripts")
class Scripts : WithLifecycle("Scripts") {

    companion object {
        lateinit var scriptPath: Path
            private set
        lateinit var registry: ScriptRegistry
            private set
        lateinit var scriptLoader: ScriptLoader
            private set
        lateinit var logger: Logger
            private set
        lateinit var triggerStorage: TriggerStorage
            private set
    }

    @Inject
    private fun setLogger(logger: Logger) {
        Scripts.logger = logger
    }


    private fun registerCommands() {
        Sponge.getCommandManager().register(
            this,
            CommandSpec.builder()
                .children(
                    mapOf(
                        listOf("trigger") to Trigger.spec(),
                        listOf("reload") to Reload.spec(),
                        listOf("list") to List.spec()
                    )
                )
                .build(),
            "scripts"
        )
    }

    @Listener(order = Order.LATE)
    fun onServerStart(event: GameStartedServerEvent) {

        Permissions.register(this)

        scriptPath = PixFiles.getPluginDirectory("scripts")
        registry = ScriptRegistry()
        scriptLoader = ScriptLoader(scriptPath, registry, GlobalsBuilder().build())
        triggerStorage = TriggerStorage(scriptPath.resolve("config"), registry)

        scriptLoader.load()
        triggerStorage.removeNotExisting()

        Sponge.getEventManager().registerListeners(this, SpongeListener())

        registerCommands()
    }

}
