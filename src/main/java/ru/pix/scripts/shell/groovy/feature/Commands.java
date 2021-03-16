package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;

@SuppressWarnings("unused")
public interface Commands extends GlobalGetters {

    default void serverCommand(Object command) {
        String str = command.toString();
        Sponge.getCommandManager().process(console(), str);
    }

    default void playerCommand(Player player, Object command) {
        String str = command.toString();
        Sponge.getCommandManager().process(player, str);
    }

}
