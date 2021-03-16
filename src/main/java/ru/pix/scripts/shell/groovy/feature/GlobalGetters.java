package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.Game;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.world.World;
import ru.pix.core.commons.Broadcaster;
import ru.pix.locations.Haste;
import ru.pix.multicore.packets.server.ServerPackets;
import ru.pix.rbattles.data.service.PtrService;
import ru.pix.rbattles.logic.BattleQueue;
import ru.pix.scripts.shell.groovy.base.GetGlobals;
import ru.pix.scripts.util.DynamicStorage;

@SuppressWarnings("unused")
public interface GlobalGetters extends GetGlobals {

    default Server server() {
        return getGlobals().getServer();
    }

    default ConsoleSource console() {
        return getGlobals().getConsole();
    }

    default Game game() {
        return getGlobals().getGame();
    }

    default Broadcaster debugBroadcaster() {
        return getGlobals().getScriptsBroadcaster();
    }

    default Broadcaster broadcaster() {
        return getGlobals().getBroadcaster();
    }

    default PtrService ptrService() {
        return getGlobals().getPtrService();
    }

    default BattleQueue ptrBattleQueue() {
        return getGlobals().getPtrBattleQueue();
    }

    default Haste haste() {
        return getGlobals().getHaste();
    }

    default ServerPackets net() {
        return getGlobals().getPackets();
    }

    default World world() {
        //noinspection OptionalGetWithoutIsPresent
        return server().getWorlds().stream().findFirst().get();
    }

    default DynamicStorage storage() {
        return getGlobals().getStorage();
    }

}
