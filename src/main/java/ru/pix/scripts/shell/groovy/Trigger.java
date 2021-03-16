package ru.pix.scripts.shell.groovy;

import groovy.lang.Closure;
import org.spongepowered.api.entity.living.player.Player;
import ru.pix.scripts.shell.groovy.base.EnchancedScript;

import java.util.Map;

@SuppressWarnings({"SameParameterValue", "unused"})
public abstract class Trigger extends EnchancedScript {

    protected Player player = null;
    private Map<String, Object> context = null;

    protected Object arg(String name) {
        return context.get(name);
    }

    public void emit(Player player, Map<String, Object> context) {
        this.player = player;
        this.context = context;
        run();
    }

    public void ifSource(String source, Closure<?> closure) {
        if(source.equals(arg("source"))) {
            closure.call();
        }
    }

}
