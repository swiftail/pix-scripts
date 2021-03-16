package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.entity.living.player.Player;
import ru.pix.multicore.implpacket.TextToastPacket;

import java.util.Map;

@SuppressWarnings("unused")
public interface ToastOps extends GlobalGetters {

    default void playerToast(Map<String, Object> params) {

        Player player = (Player) params.get("player");

        net().sendToOne(new TextToastPacket(
                        params.getOrDefault("text", "").toString(),
                        params.getOrDefault("subtitle", "").toString(),
                        (int) params.getOrDefault("seconds", 5)
                ), player.getUniqueId()
        );
    }

    default void serverToast(Map<String, Object> params) {
        net().sendToAll(new TextToastPacket(
                params.getOrDefault("text", "").toString(),
                params.getOrDefault("subtitle", "").toString(),
                (int) params.getOrDefault("seconds", 5)
        ));
    }

}
