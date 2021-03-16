package ru.pix.scripts.shell.groovy.feature;

import org.spongepowered.api.text.Text;

public interface TextOps {

    static Text text(Object... params) {
        return Text.of(params);
    }

}
