package ru.pix.scripts.shell

import ru.pix.scripts.ScriptKind
import ru.pix.scripts.shell.groovy.base.ScriptBase

class ScriptRegistry {

    private var registry = mapOf<ScriptKind<*>, Set<CompiledScript<*>>>()
    private var byId = mapOf<String,CompiledScript<*>>()

    fun set(scripts: Map<ScriptKind<*>, Set<CompiledScript<*>>>) {
        this.registry = scripts
        this.byId = scripts.flatMap { it.value }.map { it.id to it }.toMap()
    }

    fun <T : ScriptBase> getScript(kind: ScriptKind<T>, id: String): CompiledScript<T>? {
        @Suppress("UNCHECKED_CAST")
        return byId[id] as? CompiledScript<T>
    }

    fun getAny(id: String) = byId[id]

    fun <T : ScriptBase> getAllForKind(kind: ScriptKind<T>): Set<CompiledScript<T>> {
        @Suppress("UNCHECKED_CAST")
        return registry.getOrDefault(kind, setOf()) as Set<CompiledScript<T>>
    }

    fun getIds(): Set<String> = byId.keys

    fun getIdsForKind(kind: ScriptKind<*>) = getAllForKind(kind).map { it.id }

    fun has(id: String) = byId.containsKey(id)

    fun getAll() = registry


}
