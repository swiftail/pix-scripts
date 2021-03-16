package ru.pix.scripts.shell.globals

import org.spongepowered.api.Game
import org.spongepowered.api.Server
import org.spongepowered.api.command.source.ConsoleSource
import ru.pix.core.commons.Broadcaster
import ru.pix.locations.Haste
import ru.pix.multicore.packets.server.ServerPackets
import ru.pix.rbattles.data.service.PtrService
import ru.pix.rbattles.logic.BattleQueue
import ru.pix.scripts.util.DynamicStorage

data class Globals (
    val server: Server,
    val console: ConsoleSource,
    val game: Game,
    val scriptsBroadcaster: Broadcaster,
    val broadcaster: Broadcaster,
    val ptrService: PtrService,
    val ptrBattleQueue: BattleQueue,
    val haste: Haste,
    val packets: ServerPackets,
    val storage: DynamicStorage
)
