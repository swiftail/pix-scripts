package ru.pix.scripts.shell.globals

import org.spongepowered.api.text.Text
import ru.pix.core.commons.Broadcaster
import ru.pix.core.commons.getConsole
import ru.pix.core.commons.getGame
import ru.pix.core.commons.getServer
import ru.pix.locations.Haste
import ru.pix.multicore.packets.server.ServerPackets
import ru.pix.rbattles.Rbattles
import ru.pix.scripts.util.DynamicStorage

class GlobalsBuilder {

    fun build(): Globals {
        return Globals(
            server = getServer(),
            console = getConsole(),
            game = getGame(),
            scriptsBroadcaster = Broadcaster(Text.of("Scripts > ")),
            ptrService = Rbattles.ptrService,
            ptrBattleQueue = Rbattles.battleQueue,
            haste = Haste,
            packets = ServerPackets,
            broadcaster = Broadcaster(Text.of()),
            storage = DynamicStorage
        )
    }

}
