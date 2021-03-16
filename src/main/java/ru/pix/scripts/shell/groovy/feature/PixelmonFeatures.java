package ru.pix.scripts.shell.groovy.feature;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.spongepowered.api.entity.living.player.Player;

@SuppressWarnings("unused")
public interface PixelmonFeatures {

    static PlayerPartyStorage party(Player player) {
        return Pixelmon.storageManager.getParty(player.getUniqueId());
    }

}
