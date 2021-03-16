package ru.pix.scripts.trigger

import com.google.gson.reflect.TypeToken
import ru.pix.core.data.DataobjectsFormatGson
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.shell.CompiledScript
import ru.pix.scripts.shell.ScriptRegistry
import ru.pix.scripts.shell.groovy.Trigger
import ru.swiftail.dataobjects.api.DataObject
import ru.swiftail.dataobjects.impl.CachedDataObject
import ru.swiftail.dataobjects.storage.FileStorage
import java.nio.file.Path

class TriggerStorage(dir: Path, private val registry: ScriptRegistry) {

    private val dataObject = CachedDataObject.from(
        DataObject
            .builder(Triggers::class.java)
            .setDataFormat(
                DataobjectsFormatGson.create()
                    .setup { builder ->
                        val type = TypeToken.get(TriggerType::class.java).type
                        builder.registerTypeAdapter(type, TriggerTypeSerializer())
                    }
            )
            .setStorage(FileStorage(dir.resolve("triggers.json")))
            .setFallback(Triggers())
            .build()
    )

    private val access = TriggerAccess()

    fun access() = access

    fun reload() = dataObject.reload()
    fun get() = dataObject.get()
    private fun save(triggers: Triggers) = dataObject.save(triggers)

    fun removeNotExisting() {

        val triggers = get()

        val validated = triggers.triggers.filter { (_, id) ->
            registry.has(id)
        }.toMutableSet()


        triggers.triggers = validated
        save(triggers)

        access.recalculate(this)

    }

    @Suppress("UNCHECKED_CAST")
    fun <T : TriggerType> entriesForType(needle: Class<T>): List<TriggerEntry<T>> {
        return get().triggers.filter { (type, _) ->
            type::class.java == needle
        }.map { it as TriggerEntry<T> }
    }

    fun <T : TriggerType> scriptsForType(needle: Class<T>): List<Pair<CompiledScript<Trigger>, T>> {
        return entriesForType(needle).map {
            registry.getScript(ScriptKind.TRIGGER, it.id)!! to it.type
        }
    }

    fun add(id: String, type: TriggerType) {
        val obj = get()
        obj.triggers.add(TriggerEntry(type, id))
        save(obj)
        access.recalculate(this)
    }

    fun remove(entry: TriggerEntry<*>): Boolean {
        val obj = get()
        val result = obj.triggers.remove(entry)
        save(obj)

        access.recalculate(this)

        return result
    }

}
