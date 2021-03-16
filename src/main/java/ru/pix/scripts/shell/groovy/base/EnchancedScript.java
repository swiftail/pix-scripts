package ru.pix.scripts.shell.groovy.base;

import ru.pix.scripts.shell.groovy.feature.*;

public abstract class EnchancedScript
        extends
        ScriptBase
        implements
        GlobalGetters,
        PixelmonFeatures,
        TextOps,
        ToastOps,
        SpongeFeatures,
        Broadcast,
        Commands,
        PlayerOps {
}
