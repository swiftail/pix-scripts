package ru.pix.scripts.shell.groovy.base;

import groovy.lang.Script;
import ru.pix.scripts.shell.globals.Globals;

public abstract class ScriptBase extends Script implements GetGlobals {

    @Override
    public Globals getGlobals() {
        return globals;
    }

    public ScriptBase setGlobals(Globals globals) {
        this.globals = globals;
        return this;
    }

    private Globals globals;

}
