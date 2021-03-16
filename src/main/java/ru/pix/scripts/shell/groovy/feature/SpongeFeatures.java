package ru.pix.scripts.shell.groovy.feature;

import groovy.lang.Closure;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Collection;

public interface SpongeFeatures extends GlobalGetters {

    default Collection<Player> players() {
        return server().getOnlinePlayers();
    }

    default void everyPlayer(Closure<?> closure) {
        for (Player player : players()) {
            closure.call(player);
        }
    }

}
