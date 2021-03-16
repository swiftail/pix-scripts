package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.text.Text;

@SuppressWarnings("unused")
public interface Broadcast extends GlobalGetters {

    default void broadcast(Text text) {
        broadcaster().globalBroadcast(text);
    }

    default void debug(Object... data) {
        debugBroadcaster().sendToConsole(Text.of(data));
    }

}
