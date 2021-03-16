package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.data.manipulator.mutable.PotionEffectData;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectType;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Map;

public interface PlayerOps {

    default void effectPlayer(Map<String, Object> data) {

        PotionEffectType type = (PotionEffectType) data.get("type");
        if (type == null) throw new IllegalStateException("Type is null!");

        int duration = (int) data.getOrDefault("duration", 10);
        int amplifier = (int) data.getOrDefault("amplifier", 5);

        Player player = (Player) data.get("player");
        if (player == null) throw new IllegalStateException("Player is null!");

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        PotionEffectData effectData = player.getOrCreate(PotionEffectData.class).get();

        effectData.addElement(PotionEffect.builder()
                .potionType(type)
                .duration(duration)
                .amplifier(amplifier)
                .build())
        ;

        player.offer(effectData);

    }

}
