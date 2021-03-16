package ru.pix.scripts.cmd

import org.spongepowered.api.command.args.CommandElement
import org.spongepowered.api.command.args.GenericArguments
import org.spongepowered.api.text.Text
import ru.pix.scripts.ScriptKind
import ru.pix.scripts.Scripts
import ru.pix.scripts.trigger.TriggerType

object ScriptArgs {

    fun script(key: String): CommandElement = GenericArguments.choices(
        Text.of(key),
        Scripts.registry::getIds,
        Scripts.registry::getAny
    )

    fun scriptForKind(key: String, kind: ScriptKind<*>) = GenericArguments.choices(
        Text.of(key),
        { Scripts.registry.getIdsForKind(kind) },
        Scripts.registry::getAny
    )

    fun triggerScript(key: String, type: Class<out TriggerType>) = GenericArguments.choices(
        Text.of(key),
        { Scripts.triggerStorage.entriesForType(type).map { it.id } },
        Scripts.registry::getAny
    )

}
