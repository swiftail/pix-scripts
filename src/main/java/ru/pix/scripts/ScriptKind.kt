package ru.pix.scripts

import ru.pix.scripts.shell.groovy.base.EnchancedScript
import ru.pix.scripts.shell.groovy.base.ScriptBase
import ru.pix.scripts.shell.groovy.Trigger

sealed class ScriptKind<T : ScriptBase>(
    val prefix: String,
    val scriptClass: Class<T>
) {

    override fun toString(): String {
        return this::class.simpleName!!
    }

    object PIXELMON_LISTENER : ScriptKind<EnchancedScript>("pix", EnchancedScript::class.java)
    object FORGE_LISTENER : ScriptKind<EnchancedScript>("frg", EnchancedScript::class.java)
    object SPONGE_LISTENER : ScriptKind<EnchancedScript>("spg", EnchancedScript::class.java)
    object TRIGGER : ScriptKind<Trigger>("trg", Trigger::class.java)

}
